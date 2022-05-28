package com.company.model.contract;

import com.company.model.contract.base.Contract;

import java.util.Date;

public class PostPaidContract extends Contract {
    private int smsSent;
    private int phoneMinutesUsed;
    private int internetMegabytesUsed;

    private PostPaidPrices prices;

    public PostPaidContract(Date creationDate, Date endingDate, int smsSent, int phoneMinutesUsed, int internetMegabytesUsed, PostPaidPrices prices) {
        super(creationDate, endingDate);
        this.smsSent = smsSent;
        this.phoneMinutesUsed = phoneMinutesUsed;
        this.internetMegabytesUsed = internetMegabytesUsed;
        this.prices = prices;
    }

    public int getSmsSent() {
        return smsSent;
    }

    public void setSmsSent(int smsSent) {
        this.smsSent = smsSent;
    }

    public int getPhoneMinutesUsed() {
        return phoneMinutesUsed;
    }

    public void setPhoneMinutesUsed(int phoneMinutesUsed) {
        this.phoneMinutesUsed = phoneMinutesUsed;
    }

    public int getInternetMegabytesUsed() {
        return internetMegabytesUsed;
    }

    public void setInternetMegabytesUsed(int internetMegabytesUsed) {
        this.internetMegabytesUsed = internetMegabytesUsed;
    }

    public PostPaidPrices getPrices() {
        return prices;
    }

    public void setPrices(PostPaidPrices prices) {
        this.prices = prices;
    }
}
