package com.wine.model;/*
package com.wine.model;

import org.springframework.data.jpa.repository.JpaRepository;

*/

import com.wine.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Judith on 22.05.2017.
 *//*

public interface ReviewDao extends JpaRepository<Review, Long> {
    public Review getReviewByWine(String wine);
}
*/
@Transactional
public interface ReviewDao extends JpaRepository<Review, Long> {
    public List<Review> findByWineId(long id);
    public List<Review> findByUsername(String username);
}