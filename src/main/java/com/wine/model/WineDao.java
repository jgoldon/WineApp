package com.wine.model;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Judith on 02.05.2017.
 */
@Transactional
public interface WineDao extends CrudRepository<Wine, Long>{
    public Wine findByName(String name);
}
