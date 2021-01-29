package com.duo.medical.ui.my;

public class HealthyArchivesListMode {
    private int mId;
    private String sickName;
    private String patient;
    private String recordDate;

    public HealthyArchivesListMode(int mId, String sickName, String patient, String recordDate) {
        this.mId = mId;
        this.sickName = sickName;
        this.patient=patient;
        this.recordDate = recordDate;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getSickName() {
        return sickName;
    }

    public void setSickName(String sickName) {
        this.sickName = sickName;
    }

    public String getPatient(){
        return patient;
    }

    public void  setPatient(String patient){
        this.patient=patient;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }
}
