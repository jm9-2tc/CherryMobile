package com.company.model.customer;

import com.company.model.contract.Contract;

import java.util.List;

public class BusinessCustomer extends Customer {
    private List<Contract> contracts;
    private String name;
    private String nip;
    private String regon;

    public BusinessCustomer(int id, List<Contract> contracts, String name, String nip, String regon) {
        super(id);
        this.contracts = contracts;
        this.name = name;
        this.nip = nip;
        this.regon = regon;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
