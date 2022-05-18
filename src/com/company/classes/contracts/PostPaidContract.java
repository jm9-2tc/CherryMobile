package com.company.classes.contracts;

public class PostPaidContract extends Contract {
    private int smsSent;
    private int phoneTimeSpent;

    public void addSms(){
        smsSent++;
    }

    public void addMinute(){
        phoneTimeSpent++;
    }

    public void extendContract(int dais){
        setEndingDate(getEndingDate().plusDays(dais));
    }

    public int getSmsSent() {
        return smsSent;
    }

    public void setSmsSent(int smsSent) {
        this.smsSent = smsSent;
    }

    public int getPhoneTimeSpent() {
        return phoneTimeSpent;
    }

    public void setPhoneTimeSpent(int phoneTimeSpent) {
        this.phoneTimeSpent = phoneTimeSpent;
    }
}
