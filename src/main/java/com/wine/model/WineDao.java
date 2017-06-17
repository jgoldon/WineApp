package com.wine.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Judith on 02.05.2017.
 */
@Transactional
public interface WineDao extends JpaRepository<Wine, Long> {
    public Wine findByName(String name);
    public Wine findById(long id);
}
