
package com.wine.wineapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Appellation {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Url")
    @Expose
    private String url;
    @SerializedName("Region")
    @Expose
    private Region region;

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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
