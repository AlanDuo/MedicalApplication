package com.duo.medical.ui.my;

public class ShopOrderListMode {
    private int orderId;
    private String orderImg;
    private String orderDesc;
    private String orderPrice;
    private int amount;
    private long goodsId;
    private long userId;
    private String goodsName;

    public ShopOrderListMode(int orderId, String orderImg, String orderDesc, String orderPrice, int amount, long goodsId, long userId,String goodsName) {
        this.orderId = orderId;
        this.orderImg = orderImg;
        this.orderDesc = orderDesc;
        this.orderPrice = orderPrice;
        this.amount = amount;
        this.goodsId = goodsId;
        this.userId = userId;
        this.goodsName=goodsName;
    }

    public ShopOrderListMode(int orderId, String orderImg, String orderDesc, String orderPrice) {
        this.orderId = orderId;
        this.orderImg = orderImg;
        this.orderDesc = orderDesc;
        this.orderPrice = orderPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
