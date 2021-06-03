package com.duo.medical.ui.my;

public class BillMode {
    private Long billId;
    private String money;
    private String purpose;
    private String intOrOut;
    private String billTime;

    public BillMode(Long billId, String money, String purpose, String intOrOut, String billTime) {
        this.billId = billId;
        this.money = money;
        this.purpose = purpose;
        this.intOrOut = intOrOut;
        this.billTime = billTime;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getIntOrOut() {
        return intOrOut;
    }

    public void setIntOrOut(String intOrOut) {
        this.intOrOut = intOrOut;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }
}
