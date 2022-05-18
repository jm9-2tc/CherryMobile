package com.company.classes.accounts;

import com.company.classes.accounts.base.Account;
import com.company.classes.contracts.Contract;

import java.util.Date;

public class IndividualAccount extends Account {
    private String pesel;
    private Date dateOfBirth;
    private Contract[] contracts = new Contract[10];

    public IndividualAccount(int id) {
        super(id);
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Contract[] getContracts() {
        return contracts;
    }

    public void setContracts(Contract[] contracts) {
        this.contracts = contracts;
    }
}
