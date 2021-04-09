package com.duo.medical.ui.my;

public class PrescriptionDetailMode {
    private String prescriptionId;

    private String username;

    private String gender;

    private String age;

    private String doctorName;

    private String doctorPhone;

    private String hospitalAddress;

    private String hospital;

    private String category;

    private String level;

    private String money;

    private String orderTime;

    private String diagnosisResult;

    private String rp;

    public PrescriptionDetailMode(String prescriptionId, String username, String gender, String age, String doctorName, String doctorPhone, String hospitalAddress, String hospital, String category, String level, String money, String orderTime, String diagnosisResult, String rp) {
        this.prescriptionId = prescriptionId;
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.hospitalAddress = hospitalAddress;
        this.hospital = hospital;
        this.category = category;
        this.level = level;
        this.money = money;
        this.orderTime = orderTime;
        this.diagnosisResult = diagnosisResult;
        this.rp = rp;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getDiagnosisResult() {
        return diagnosisResult;
    }

    public void setDiagnosisResult(String diagnosisResult) {
        this.diagnosisResult = diagnosisResult;
    }

    public String getRp() {
        return rp;
    }

    public void setRp(String rp) {
        this.rp = rp;
    }
}
