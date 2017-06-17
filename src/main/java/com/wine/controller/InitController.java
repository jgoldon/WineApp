package com.wine.controller;

import com.google.gson.Gson;
import com.wine.model.Wine;
import com.wine.model.WineDao;
import com.wine.wineapi.List;
import com.wine.wineapi.WineResponse;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
        Content content = Request.Get("http://services.wine.com/api/beta2/service.svc/json/catalog?apikey=0f354f5704c4dc771135836f2f84a997&size=10&offset=1")
                .execute().returnContent();
        Gson gson = new Gson();
        WineResponse wineResponse = gson.fromJson(content.asString(), WineResponse.class);

        for (List list : wineResponse.getProducts().getList()) {
            Wine wine = new Wine();
            wine.name = list.getName();
            wine.description = list.getDescription();
            wine.region = list.getAppellation().getRegion().getName();

            wineDao.save(wine);
        }
    }
}