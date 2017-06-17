package com.wine.controller;

import com.wine.model.Review;
import com.wine.model.ReviewDao;
import com.wine.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Judith on 22.05.2017.
 */
@RestController
@RequestMapping("api/v1/")
public class ReviewController {
    public ReviewController(ReviewDao reviewDao){
        this.reviewDao = reviewDao;
    }

    @Autowired
    private ReviewDao reviewDao;

    @RequestMapping(value  = "reviews", method = RequestMethod.GET)
    public List<Review> list(@RequestParam Long wineId){
        List<Review> result = reviewDao.findByWineId(wineId);
        return result;
    }

    @RequestMapping(value  = "reviews/{id}", method = RequestMethod.GET)
    public List<Review> get(@PathVariable Long id){
        return reviewDao.findByWineId(id);
    }

    @RequestMapping(value  = "reviews", method = RequestMethod.POST)
    public Review update(@RequestBody Review review) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        review.created = new Date().toString();
        review.username = authentication.getName();
        reviewDao.save(review);
        return review;
    }
}
