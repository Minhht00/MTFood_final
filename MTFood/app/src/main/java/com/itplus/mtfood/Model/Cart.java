package com.itplus.mtfood.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cart {
    @SerializedName("orderId")
    @Expose
    private int orderId;
    @SerializedName("customerId")
    @Expose
    private int customerId;
    @SerializedName("amount")
    @Expose
    private double amount;
    @SerializedName("address")
    @Expose
    private String address;

    public Cart() {
    }

    public Cart(int customerId, double amount, String address) {
        this.customerId = customerId;
        this.amount = amount;
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
