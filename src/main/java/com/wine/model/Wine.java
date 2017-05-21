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

    public Wine() {}
    public Wine(long id){
        this.id = id;
    }

    public Wine(String region, String name) {
        this.region = region;
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
