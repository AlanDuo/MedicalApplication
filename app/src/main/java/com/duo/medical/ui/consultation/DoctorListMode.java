package com.duo.medical.ui.consultation;

public class DoctorListMode {
    private int userId;
    private int doctorId;
    private String doctorImg;
    private String doctorName;
    private String doctorLevel;
    private String doctorCompany;
    private String doctorGood;

    public DoctorListMode(int userId,int doctorId,String doctorImg,String doctorName,String doctorLevel,String doctorCompany,String doctorGood){
        this.userId=userId;
        this.doctorId=doctorId;
        this.doctorImg=doctorImg;
        this.doctorName=doctorName;
        this.doctorLevel=doctorLevel;
        this.doctorCompany=doctorCompany;
        this.doctorGood=doctorGood;
    }
    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId=userId;
    }
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorImg() {
        return doctorImg;
    }

    public void setDoctorImg(String doctorImg) {
        this.doctorImg = doctorImg;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorLevel() {
        return doctorLevel;
    }

    public void setDoctorLevel(String doctorLevel) {
        this.doctorLevel = doctorLevel;
    }

    public String getDoctorCompany() {
        return doctorCompany;
    }

    public void setDoctorCompany(String doctorCompany) {
        this.doctorCompany = doctorCompany;
    }

    public String getDoctorGood() {
        return doctorGood;
    }

    public void setDoctorGood(String doctorGood) {
        this.doctorGood = doctorGood;
    }
}
