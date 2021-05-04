package com.duo.medical.ui.my;

public class ShopCartMode {
    private String goodsId;
    private String shopImg;
    private String shopDesc;
    private String shopPrice;
    private int shopAmount;
    private int select;

    public ShopCartMode(String goodsId,String shopImg, String shopDesc, String shopPrice, int shopAmount,int select) {
        this.goodsId=goodsId;
        this.shopImg = shopImg;
        this.shopDesc = shopDesc;
        this.shopPrice = shopPrice;
        this.shopAmount = shopAmount;
        this.select=select;
    }

    public String getGoodsId(){
        return goodsId;
    }

    public void setGoodsId(String goodsId){
        this.goodsId=goodsId;
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
    public int getSelect(){
        return select;
    }
    public void setSelect(int select){
        this.select=select;
    }
}
