package com.wine.model;

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
    private String region;

    @NotNull
    private String name;

    public Wine() {}
    public Wine(long id){
        this.id = id;
    }

    public Wine(String region, String name) {
        this.region = region;
        this.name = name;
    }
}
