package com.wine.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Judith on 22.05.2017.
 */
@Entity
@Table(name = "Reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    public long wineId;

    @NotNull
    public String username;

    @NotNull
    public String body;

    @NotNull
    public String created;

    @NotNull
    public int stars;
}
