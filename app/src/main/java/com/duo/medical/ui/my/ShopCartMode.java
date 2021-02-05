package com.duo.medical.ui.my;

public class ShopCartMode {
    private int shopCartId;
    private String shopImg;
    private String shopDesc;
    private String shopType;
    private String shopPrice;
    private int shopAmount;

    public ShopCartMode(int shopCartId, String shopImg, String shopDesc, String shopType, String shopPrice, int shopAmount) {
        this.shopCartId = shopCartId;
        this.shopImg = shopImg;
        this.shopDesc = shopDesc;
        this.shopType = shopType;
        this.shopPrice = shopPrice;
        this.shopAmount = shopAmount;
    }

    public int getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(int shopCartId) {
        this.shopCartId = shopCartId;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public int getShopAmount() {
        return shopAmount;
    }

    public void setShopAmount(int shopAmount) {
        this.shopAmount = shopAmount;
    }
}
