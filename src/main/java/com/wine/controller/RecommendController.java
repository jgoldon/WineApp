package com.wine.controller;

import com.wine.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Judith on 06.06.2017.
 */
@RestController
@RequestMapping("api/v1/")
public class RecommendController {
    private WineDao wineDao;
    private ReviewDao reviewDao;
    public RecommendController(WineDao wineDao, ReviewDao reviewDao) {
        this.wineDao = wineDao;
        this.reviewDao = reviewDao;
    }

    @RequestMapping(value  = "recommends", method = RequestMethod.GET)
    public List<Recommend> getRecommenadtions()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Review> usersReviews = reviewDao.findByUsername(authentication.getName());
        List<Wine> reviewedWines = new ArrayList<Wine>();
        for(Review usersReview : usersReviews){
            reviewedWines.add(wineDao.findById(usersReview.wineId));
        }

        //Page<Wine> all = wineDao.findAll(new PageRequest(0, 5));
        //List<Wine> content = all.getContent();

        List<Recommend> collect = new ArrayList<Recommend>();

        for (Wine wine : reviewedWines) {
            Recommend recommend = new Recommend();
            recommend.wine = wine;
            recommend.value = 99;

            collect.add(recommend);
        }

        return collect;
    }
    //public RecommendController(RecommendDao recommendDao){Authentication authentication = SecurityContextHolder.getContext().getAuthentication();}
}
