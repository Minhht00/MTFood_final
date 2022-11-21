package com.itplus.mtfood.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartDetail implements Serializable {
    @SerializedName("orderDetailId")
    @Expose
    private int orderDetailId;
    @SerializedName("orderId")
    @Expose
    private int orderId;
    @SerializedName("productId")
    @Expose
    private int productId;

    @SerializedName("categoryId")
    @Expose
    private int categoryId;

    @SerializedName("unitPrice")
    @Expose
    private double unitPrice;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String pic;
    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("quantity")
    @Expose
    private int numberInCart;

    public CartDetail() {
    }

    public CartDetail(String name, String pic, String description, double unitPrice, int numberInCart) {
        this.name = name;
        this.pic = pic;
        this.unitPrice = unitPrice;
        this.numberInCart = getNumberInCart();
    }

    public CartDetail(String name, String pic, String description, double unitPrice) {
        this.name = name;
        this.pic = pic;
        this.description = description;
        this.unitPrice = unitPrice;
    }

    public CartDetail(int orderDetailId, int orderId, int productId, int numberInCart, double unitPrice) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.numberInCart = numberInCart;
        this.unitPrice = unitPrice;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
