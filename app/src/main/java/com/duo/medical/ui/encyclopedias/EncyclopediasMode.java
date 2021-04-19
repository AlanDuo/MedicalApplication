package com.duo.medical.ui.encyclopedias;

public class EncyclopediasMode {
    private String picUrl;
    private String desc;
    private String shareTime;
    private int mId;

    public EncyclopediasMode(String desc,String picUrl,String shareTime,int mId){
        this.desc=desc;
        this.picUrl=picUrl;
        this.shareTime=shareTime;
        this.mId=mId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getShareTime() {
        return shareTime;
    }

    public void setShareTime(String shareTime) {
        this.shareTime = shareTime;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
}
