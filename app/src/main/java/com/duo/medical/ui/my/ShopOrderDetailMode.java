package com.duo.medical.ui.my;

public class ShopOrderDetailMode {
    private String orderId;

    private String orderNum;

    private String goodsId;

    private String username;

    private String phone;

    private String address;

    private String goodsImg;

    private String goodsName;

    private String goodsDesc;

    private String price;

    private String amount;

    private String logisticsNum;

    private String status;

    private String createTime;

    private String payTime;

    private String deliverTime;

    private String receiveTime;

    private String finishTime;

    public ShopOrderDetailMode(String orderId, String orderNum, String goodsId, String username,String phone, String address, String goodsImg, String goodsName, String goodsDesc, String price,String amount, String logisticsNum, String status, String createTime, String payTime, String deliverTime, String receiveTime, String finishTime) {
        this.orderId = orderId;
        this.orderNum = orderNum;
        this.goodsId = goodsId;
        this.username = username;
        this.phone=phone;
        this.address = address;
        this.goodsImg = goodsImg;
        this.goodsName = goodsName;
        this.goodsDesc = goodsDesc;
        this.price = price;
        this.amount=amount;
        this.logisticsNum = logisticsNum;
        this.status = status;
        this.createTime = createTime;
        this.payTime = payTime;
        this.deliverTime = deliverTime;
        this.receiveTime = receiveTime;
        this.finishTime = finishTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone=phone;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount(){
        return amount;
    }

    public void setAmount(String amount){
        this.amount=amount;
    }

    public String getLogisticsNum() {
        return logisticsNum;
    }

    public void setLogisticsNum(String logisticsNum) {
        this.logisticsNum = logisticsNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
