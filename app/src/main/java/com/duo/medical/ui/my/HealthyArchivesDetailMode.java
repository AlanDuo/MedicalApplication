package com.duo.medical.ui.my;

public class HealthyArchivesDetailMode {
    private String recordId;

    private String username;

    private String userImg;

    private String gender;

    private String age;

    private String relationship;

    private String diagnosisName;

    private String inTime;

    private String outTime;

    private String prescription;

    private String remarks;

    private String visitHospital;

    private String treatmentDept;

    private String doctor;

    private String phone;

    public HealthyArchivesDetailMode(String recordId, String username, String userImg, String gender, String age, String relationship, String diagnosisName, String inTime, String outTime, String prescription, String remarks, String visitHospital, String treatmentDept, String doctor, String phone) {
        this.recordId = recordId;
        this.username = username;
        this.userImg = userImg;
        this.gender = gender;
        this.age = age;
        this.relationship = relationship;
        this.diagnosisName = diagnosisName;
        this.inTime = inTime;
        this.outTime = outTime;
        this.prescription = prescription;
        this.remarks = remarks;
        this.visitHospital = visitHospital;
        this.treatmentDept = treatmentDept;
        this.doctor = doctor;
        this.phone = phone;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getDiagnosisName() {
        return diagnosisName;
    }

    public void setDiagnosisName(String diagnosisName) {
        this.diagnosisName = diagnosisName;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getVisitHospital() {
        return visitHospital;
    }

    public void setVisitHospital(String visitHospital) {
        this.visitHospital = visitHospital;
    }

    public String getTreatmentDept() {
        return treatmentDept;
    }

    public void setTreatmentDept(String treatmentDept) {
        this.treatmentDept = treatmentDept;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
