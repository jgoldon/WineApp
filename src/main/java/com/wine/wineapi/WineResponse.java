
package com.wine.wineapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WineResponse {

    @SerializedName("Products")
    @Expose
    private Products products;

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

}
