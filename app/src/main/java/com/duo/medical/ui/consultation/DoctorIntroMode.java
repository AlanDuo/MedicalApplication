package com.duo.medical.ui.consultation;

public class DoctorIntroMode {
    private String doctorId;

    private String username;

    private String userImg;

    private String hospital;

    private String category;

    private String level;

    private String material;

    private String intro;

    private String star;

    private String goodAt;

    public DoctorIntroMode(String doctorId, String username, String userImg, String hospital, String category, String level, String material, String intro, String star,String goodAt) {
        this.doctorId = doctorId;
        this.username = username;
        this.userImg = userImg;
        this.hospital = hospital;
        this.category = category;
        this.level = level;
        this.material = material;
        this.intro = intro;
        this.star = star;
        this.goodAt=goodAt;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
    public String getGoodAt(){
        return goodAt;
    }
    public void setGoodAt(String goodAt){
        this.goodAt=goodAt;
    }
}
