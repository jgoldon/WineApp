package com.wine.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Judith on 02.05.2017.
 */

@Entity
@Table(name = "Wines")
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    public String region;

    @NotNull
    public String name;

    @NotNull
    public String category;

    @NotNull
    public String varietal;

    @NotNull
    public int vintage;

    public String description;

    public Wine() {}
    public Wine(long id){
        this.id = id;
    }

    public Wine(String region, String name, String category, String varietal, int vintage, String description, double rating) {
        this.region = region;
        this.name = name;
        this.category = category;
        this.varietal = varietal;
        this.vintage = vintage;
        this.description = description;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
