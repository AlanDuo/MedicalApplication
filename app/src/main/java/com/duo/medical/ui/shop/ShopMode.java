package com.duo.medical.ui.shop;

public class ShopMode {
    private int id;
    private String img_url;
    private String desc;
    private String price;

    public ShopMode(int id,String img_url,String desc,String price){
        this.id=id;
        this.img_url=img_url;
        this.desc=desc;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getPrice(){
        return price;
    }
    public void setPrice(String price){
        this.price=price;
    }
}
