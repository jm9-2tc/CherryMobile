package com.company.classes.contracts;

public class PrePaidContract extends Contract {
    private int smsCount;
    private int phoneTimeLeft;
    private float contractMoney;

    public void extendContract(float additionalPayment) {
        setContractMoney(getContractMoney() + additionalPayment);
    }

    public void lowerSmsCount(){
        smsCount--;
    }

    public void lowerPhoneTimeLeft(){
        phoneTimeLeft--;
    }

    public int getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(int smsCount) {
        this.smsCount = smsCount;
    }

    public int getPhoneTimeLeft() {
        return phoneTimeLeft;
    }

    public void setPhoneTimeLeft(int phoneTimeLeft) {
        this.phoneTimeLeft = phoneTimeLeft;
    }

    public float getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(float contractMoney) {
        this.contractMoney = contractMoney;
    }
}
