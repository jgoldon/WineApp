package com.wine.model;

import java.util.ArrayList;
import java.util.List;

import com.wine.model.WineDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Created by Judith on 07.06.2017.
 */
public class Recommend implements Comparable<Recommend> {
    public Wine wine;
    public long value;

    public Recommend(Wine wine, long value)
    {
        this.wine = wine;
        this.value = value;
    }

    @Override
    public int compareTo(Recommend recommend) {
        return (int)(recommend.value - value);
    }
}
