
package com.wine.wineapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Products {

    @SerializedName("List")
    @Expose
    private java.util.List<com.wine.wineapi.List> list = null;

    public java.util.List<com.wine.wineapi.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.wine.wineapi.List> list) {
        this.list = list;
    }

}
