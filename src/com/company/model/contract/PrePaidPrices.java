package com.company.model.contract;

public class PrePaidPrices {
    private int smsPrice;
    private int phoneMinutePrice;
    private int internetMegabytePrice;

    public PrePaidPrices(int smsPrice, int phoneMinutePrice, int internetMegabytePrice) {
        this.smsPrice = smsPrice;
        this.phoneMinutePrice = phoneMinutePrice;
        this.internetMegabytePrice = internetMegabytePrice;
    }

    public int getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(int smsPrice) {
        this.smsPrice = smsPrice;
    }

    public int getPhoneMinutePrice() {
        return phoneMinutePrice;
    }

    public void setPhoneMinutePrice(int phoneMinutePrice) {
        this.phoneMinutePrice = phoneMinutePrice;
    }

    public int getInternetMegabytePrice() {
        return internetMegabytePrice;
    }

    public void setInternetMegabytePrice(int internetMegabytePrice) {
        this.internetMegabytePrice = internetMegabytePrice;
    }
}
