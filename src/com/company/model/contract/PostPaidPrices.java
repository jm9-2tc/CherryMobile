package com.company.model.contract;

public class PostPaidPrices {
    private int contractPrice;

    private int smsCountInPrice;
    private int phoneMinutesCountInPrice;
    private int internetMegabytesCountInPrice;

    private int smsAboveContractPrice;
    private int phoneMinuteAboveContractPrice;
    private int internetMegabyteAboveContractPrice;

    public PostPaidPrices(int contractPrice, int smsCountInPrice, int phoneMinutesCountInPrice, int internetMegabytesCountInPrice, int smsAboveContractPrice, int phoneMinuteAboveContractPrice, int internetMegabyteAboveContractPrice) {
        this.contractPrice = contractPrice;
        this.smsCountInPrice = smsCountInPrice;
        this.phoneMinutesCountInPrice = phoneMinutesCountInPrice;
        this.internetMegabytesCountInPrice = internetMegabytesCountInPrice;
        this.smsAboveContractPrice = smsAboveContractPrice;
        this.phoneMinuteAboveContractPrice = phoneMinuteAboveContractPrice;
        this.internetMegabyteAboveContractPrice = internetMegabyteAboveContractPrice;
    }

    public int getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(int contractPrice) {
        this.contractPrice = contractPrice;
    }

    public int getSmsCountInPrice() {
        return smsCountInPrice;
    }

    public void setSmsCountInPrice(int smsCountInPrice) {
        this.smsCountInPrice = smsCountInPrice;
    }

    public int getPhoneMinutesCountInPrice() {
        return phoneMinutesCountInPrice;
    }

    public void setPhoneMinutesCountInPrice(int phoneMinutesCountInPrice) {
        this.phoneMinutesCountInPrice = phoneMinutesCountInPrice;
    }

    public int getInternetMegabytesCountInPrice() {
        return internetMegabytesCountInPrice;
    }

    public void setInternetMegabytesCountInPrice(int internetMegabytesCountInPrice) {
        this.internetMegabytesCountInPrice = internetMegabytesCountInPrice;
    }

    public int getSmsAboveContractPrice() {
        return smsAboveContractPrice;
    }

    public void setSmsAboveContractPrice(int smsAboveContractPrice) {
        this.smsAboveContractPrice = smsAboveContractPrice;
    }

    public int getPhoneMinuteAboveContractPrice() {
        return phoneMinuteAboveContractPrice;
    }

    public void setPhoneMinuteAboveContractPrice(int phoneMinuteAboveContractPrice) {
        this.phoneMinuteAboveContractPrice = phoneMinuteAboveContractPrice;
    }

    public int getInternetMegabyteAboveContractPrice() {
        return internetMegabyteAboveContractPrice;
    }

    public void setInternetMegabyteAboveContractPrice(int internetMegabyteAboveContractPrice) {
        this.internetMegabyteAboveContractPrice = internetMegabyteAboveContractPrice;
    }
}
