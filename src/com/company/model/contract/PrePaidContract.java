package com.company.model.contract;

public class PrePaidContract extends Contract {
    private int smsCount;
    private int phoneMinutesCount;
    private int internetMegabytesCount;

    private int contractMoney;
    private PrePaidPrices prices;

    public PrePaidContract(int smsCount, int phoneMinutesCount, int internetMegabytesCount, int contractMoney, PrePaidPrices prices) {
        this.smsCount = smsCount;
        this.phoneMinutesCount = phoneMinutesCount;
        this.internetMegabytesCount = internetMegabytesCount;
        this.contractMoney = contractMoney;
        this.prices = prices;
    }

    public int getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(int smsCount) {
        this.smsCount = smsCount;
    }

    public int getPhoneMinutesCount() {
        return phoneMinutesCount;
    }

    public void setPhoneMinutesCount(int phoneMinutesCount) {
        this.phoneMinutesCount = phoneMinutesCount;
    }

    public int getInternetMegabytesCount() {
        return internetMegabytesCount;
    }

    public void setInternetMegabytesCount(int internetMegabytesCount) {
        this.internetMegabytesCount = internetMegabytesCount;
    }

    public int getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(int contractMoney) {
        this.contractMoney = contractMoney;
    }

    public PrePaidPrices getPrices() {
        return prices;
    }

    public void setPrices(PrePaidPrices prices) {
        this.prices = prices;
    }
}
