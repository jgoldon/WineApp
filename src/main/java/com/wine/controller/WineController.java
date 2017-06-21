package com.wine.controller;

import com.wine.model.ReviewDao;
import com.wine.model.Wine;
import com.wine.model.WineDao;
import org.hibernate.*;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.SynchronizationType;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Judith on 03.05.2017.
 */
@RestController
@RequestMapping("api/v1/")
public class WineController {
    public WineController(WineDao wineDao, ReviewDao reviewDao){
        this.wineDao = wineDao;
        this.reviewDao = reviewDao;
    }

    @Autowired
    private WineDao wineDao;

    @Autowired
    private ReviewDao reviewDao;

    @RequestMapping(value  = "wines", method = RequestMethod.GET)
    public List<Wine> list(){
        List<Wine> result = wineDao.findAll();
        return result;
    }

    @RequestMapping(value  = "wines", method = RequestMethod.POST)
    public Wine create(@RequestBody Wine wine){
        //Wine wine2 = new Wine("lasmd", "sdds");
        return wineDao.save(wine);
    }

    @RequestMapping(value  = "wines/{id}", method = RequestMethod.GET)
    public Wine get(@PathVariable Long id){
        return wineDao.findOne(id);
    }

    @RequestMapping(value  = "wines/{id}", method = RequestMethod.PUT)
    public Wine update(@PathVariable Long id, @RequestBody Wine wine){
        Wine entity = wineDao.findOne(id);
        entity.name = wine.name;
        entity.region = wine.region;
        entity.category = wine.category;
        entity.varietal = wine.varietal;
        entity.vintage = wine.vintage;
        entity.description = wine.description;
        wineDao.save(entity);
        return entity;
    }

    @RequestMapping(value  = "wines/{id}", method = RequestMethod.DELETE)
    public Wine delete(@PathVariable Long id){
        Wine entity = wineDao.findOne(id);
        wineDao.delete(entity);
        return entity;
    }
}
