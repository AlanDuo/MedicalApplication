package com.duo.medical.ui.my;

public class ShopOrderListMode {
    private int orderId;
    private String orderImg;
    private String orderDesc;
    private String orderPrice;

    public ShopOrderListMode(int orderId, String orderImg, String orderDesc, String orderPrice) {
        this.orderId = orderId;
        this.orderImg = orderImg;
        this.orderDesc = orderDesc;
        this.orderPrice = orderPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderImg() {
        return orderImg;
    }

    public void setOrderImg(String orderImg) {
        this.orderImg = orderImg;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }
}
