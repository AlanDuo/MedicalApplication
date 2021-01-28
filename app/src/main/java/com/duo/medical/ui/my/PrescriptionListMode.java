package com.duo.medical.ui.my;

public class PrescriptionListMode {
    private int mId;
    private String doctorImg;
    private String doctorName;
    private String doctorCompany;
    private String result;

    public PrescriptionListMode(int mId, String doctorImg, String doctorName, String doctorCompany, String result) {
        this.mId = mId;
        this.doctorImg = doctorImg;
        this.doctorName = doctorName;
        this.doctorCompany = doctorCompany;
        this.result = result;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
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

    public String getDoctorCompany() {
        return doctorCompany;
    }

    public void setDoctorCompany(String doctorCompany) {
        this.doctorCompany = doctorCompany;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
