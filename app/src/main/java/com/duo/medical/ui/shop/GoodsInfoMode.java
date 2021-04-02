package com.duo.medical.ui.shop;

public class GoodsInfoMode {
    private String goodsId;

    private String goodsName;

    private String goodsImg;

    private String introImg;

    private String goodsDesc;

    private String goodsType;

    private String goodsPurpose;

    private String goodsSource;

    private String wholesalePrice;

    private String stock;

    private String evaUserId;

    private String evaUsername;

    private String evaUserImg;

    private String evaContent;

    public GoodsInfoMode(String goodsId, String goodsName, String goodsImg, String introImg, String goodsDesc, String goodsType, String goodsPurpose, String goodsSource, String wholesalePrice, String stock, String evaUserId, String evaUsername, String evaUserImg, String evaContent) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsImg = goodsImg;
        this.introImg = introImg;
        this.goodsDesc = goodsDesc;
        this.goodsType = goodsType;
        this.goodsPurpose = goodsPurpose;
        this.goodsSource = goodsSource;
        this.wholesalePrice = wholesalePrice;
        this.stock = stock;
        this.evaUserId = evaUserId;
        this.evaUsername = evaUsername;
        this.evaUserImg = evaUserImg;
        this.evaContent = evaContent;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getIntroImg() {
        return introImg;
    }

    public void setIntroImg(String introImg) {
        this.introImg = introImg;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsPurpose() {
        return goodsPurpose;
    }

    public void setGoodsPurpose(String goodsPurpose) {
        this.goodsPurpose = goodsPurpose;
    }

    public String getGoodsSource() {
        return goodsSource;
    }

    public void setGoodsSource(String goodsSource) {
        this.goodsSource = goodsSource;
    }

    public String getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(String wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getEvaUserId() {
        return evaUserId;
    }

    public void setEvaUserId(String evaUserId) {
        this.evaUserId = evaUserId;
    }

    public String getEvaUsername() {
        return evaUsername;
    }

    public void setEvaUsername(String evaUsername) {
        this.evaUsername = evaUsername;
    }

    public String getEvaUserImg() {
        return evaUserImg;
    }

    public void setEvaUserImg(String evaUserImg) {
        this.evaUserImg = evaUserImg;
    }

    public String getEvaContent() {
        return evaContent;
    }

    public void setEvaContent(String evaContent) {
        this.evaContent = evaContent;
    }
}
