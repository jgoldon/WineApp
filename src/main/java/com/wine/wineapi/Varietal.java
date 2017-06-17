
package com.wine.wineapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Varietal {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Url")
    @Expose
    private String url;
    @SerializedName("WineType")
    @Expose
    private WineType wineType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WineType getWineType() {
        return wineType;
    }

    public void setWineType(WineType wineType) {
        this.wineType = wineType;
    }

}
