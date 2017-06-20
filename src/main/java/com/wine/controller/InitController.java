package com.wine.controller;

import com.google.gson.Gson;
import com.wine.model.Wine;
import com.wine.model.WineDao;
import com.wine.wineapi.List;
import com.wine.wineapi.ProductAttribute;
import com.wine.wineapi.WineResponse;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Judith on 17.06.2017.
 */
@RestController
@RequestMapping("api/v1/")
public class InitController {
    private WineDao wineDao;

    public InitController(WineDao wineDao) {
        this.wineDao = wineDao;
    }

    @RequestMapping(value  = "init", method = RequestMethod.POST)
    public void Init() throws IOException {
        Content content = Request.Get("http://services.wine.com/api/beta2/service.svc/json/catalog?apikey=0f354f5704c4dc771135836f2f84a997&size=1000&offset=1")
                .execute().returnContent();
        Gson gson = new Gson();
        WineResponse wineResponse = gson.fromJson(content.asString(), WineResponse.class);
        java.util.List<List> lists = new ArrayList<List>();
        for (List wineList : wineResponse.getProducts().getList()) {
            String type = wineList.getType();
            if (type.equals("Wine"))
                lists.add(wineList);
        }
        for (List list : lists) {
            Wine wine = new Wine();
            wine.name = list.getName();
            wine.description = list.getDescription();
            wine.region = list.getAppellation().getRegion().getName();
            wine.vintage = !list.getVintage().equals("Non-Vintage") ? Integer.parseInt(list.getVintage()) : 2017;
            wine.varietal = list.getVarietal().getWineType().getName();
            java.util.List<ProductAttribute> productAttributes = list.getProductAttributes();
            java.util.List<ProductAttribute> filterResult = new ArrayList<ProductAttribute>();
            for (ProductAttribute productAttribute : productAttributes) {
                if(productAttribute.getId() > 600 && productAttribute.getId() <700)
                    filterResult.add(productAttribute);
            }

            if(filterResult.isEmpty() == false)
            {
                String categoryName = filterResult.get(0).getName();
                if(categoryName.equals("Big &amp; Bold") || categoryName.equals("Rich &amp; Creamy"))
                    wine.category = "Dry";
                else if(categoryName.equals("Light &amp; Crisp") || categoryName.equals("Light &amp; Fruity") || categoryName.equals("Sweet"))
                    wine.category = "Sweet";
                else
                    wine.category = "SemiDry";

                wineDao.save(wine);

            }
        }
    }
}