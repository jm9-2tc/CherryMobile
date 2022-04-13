package com.company.classes.accounts;

import com.company.classes.accounts.base.Account;
import com.company.classes.contracts.base.Contract;

import java.util.List;

public class CompanyAccount extends Account {
    private List<Contract> contracts;
    private String name;
    private String nip;

    public CompanyAccount(int id){
        super(id);
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
}
