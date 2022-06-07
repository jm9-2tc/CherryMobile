package com.company.model.customer;

import com.company.model.contract.base.Contract;
import com.company.model.customer.base.Customer;

import java.util.List;

public class BusinessCustomer extends Customer {
    private List<Contract> contracts;
    private String nip;
    private String regon;
    private String name;
    private List<Address> addressList;

    public BusinessCustomer(String phoneNumber, String email, String password, String nip, String regon, String name, List<Address> addressList) {
        super(phoneNumber, email, password);
        this.nip = nip;
        this.regon = regon;
        this.name = name;
        this.addressList = addressList;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
