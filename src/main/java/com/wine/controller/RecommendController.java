package com.wine.controller;

import com.wine.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    public List<Recommend> recommendAlgorithm()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        /* Stary algorytm:
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
        }*/


        // Algorytm bazuje na polach "category" oraz "varietal".
        // Brak oceny -> 50% dopasowania.
        // 1 -> 0%
        // 2 -> 25%
        // 3 -> 50%
        // 4 -> 75%
        // 5 -> 100%

        List<Recommend> collect = new ArrayList<Recommend>();
        Map<String, RecommendMapValue> categoryMap = new TreeMap<String, RecommendMapValue>();
        Map<String, RecommendMapValue> varietalMap = new TreeMap<String, RecommendMapValue>();

        // 1. Wypełnienie map danymi początkowymi:
        for (Wine wine : wineDao.findAll()) {
            categoryMap.put(wine.category, new RecommendMapValue(50, 1, 0));
            varietalMap.put(wine.varietal, new RecommendMapValue(50, 1, 0));
        }

        // 2. Dodanie danych z tabeli "reviews" do map:
        for (Review review : reviewDao.findByUsername(authentication.getName())) {
            long starPower = (review.stars-1)*25;
            Wine wine = wineDao.findById(review.wineId);
            categoryMap.get(wine.category).sum += starPower;
            categoryMap.get(wine.category).count += 1;
            varietalMap.get(wine.varietal).sum += starPower;
            varietalMap.get(wine.varietal).count += 1;
        }

        // 3. Wylicz średnie:
        for (Map.Entry<String, RecommendMapValue> entry : categoryMap.entrySet())
        {
            entry.getValue().average = entry.getValue().sum / entry.getValue().count;
        }
        for (Map.Entry<String, RecommendMapValue> entry : varietalMap.entrySet())
        {
            entry.getValue().average = entry.getValue().sum / entry.getValue().count;
        }

        // 4. Wypełnij listę rekomendacji danymi:
        for (Wine wine : wineDao.findAll())
        {
            collect.add(new Recommend(wine, (categoryMap.get(wine.category).average + varietalMap.get(wine.varietal).average) / 2));
        }

        // 5. Posortuj kolekcję:
        Collections.sort(collect);
        return collect;
    }
    @RequestMapping(value  = "recommends", method = RequestMethod.GET)
    public List<Recommend> getRecommenadtions()
    {
        List<Recommend> collect2 = recommendAlgorithm().subList(0, 10);

        return collect2;
    }

    @RequestMapping(value  = "oddities", method = RequestMethod.GET)
    public List<Recommend> getOddities()
    {
        List<Recommend> collect2 = recommendAlgorithm().subList(recommendAlgorithm().size() - 10, recommendAlgorithm().size());

        return collect2;
    }
    //public RecommendController(RecommendDao recommendDao){Authentication authentication = SecurityContextHolder.getContext().getAuthentication();}
}
