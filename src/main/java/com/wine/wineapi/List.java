
package com.wine.wineapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Appellation")
    @Expose
    private Appellation appellation;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Varietal")
    @Expose
    private Varietal varietal;
    @SerializedName("Vintage")
    @Expose
    private String vintage;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ProductAttributes")
    @Expose
    private java.util.List<ProductAttribute> productAttributes = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Appellation getAppellation() {
        return appellation;
    }

    public void setAppellation(Appellation appellation) {
        this.appellation = appellation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Varietal getVarietal() {
        return varietal;
    }

    public void setVarietal(Varietal varietal) {
        this.varietal = varietal;
    }

    public String getVintage() {
        return vintage;
    }

    public void setVintage(String vintage) {
        this.vintage = vintage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(java.util.List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

}
