package com.itplus.mtfood.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
    @SerializedName("productId")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String pic;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("unitPrice")
    @Expose
    private int fee;
    @SerializedName("quantity")
    @Expose
    private int numberInCart;


    public Product() {
    }

    public Product(String name, String pic, String description, int fee, int numberInCart) {
        this.name = name;
        this.pic = pic;
        this.fee = fee;
        this.numberInCart = getNumberInCart();
    }

    public Product(String name, String pic, String description, int fee) {
        this.name = name;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

}
