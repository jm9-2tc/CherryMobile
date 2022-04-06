package com.company.classes.accounts;

import com.company.classes.accounts.base.Account;
import com.company.classes.contracts.base.Contract;

public class ClientAccount extends Account {
    private Contract[] contracts = new Contract[10];
    private String contactNumber;
    private String email;
    private String address;

    public ClientAccount(int id) {
        super(id);
    }

    public Contract[] getContracts() {
        return contracts;
    }

    public void setContracts(Contract[] contracts) {
        this.contracts = contracts;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
