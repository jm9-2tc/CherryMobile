package com.company.model.customer;

import com.company.model.contract.Contract;

import java.util.Date;

public class IndividualCustomer extends Customer {
    private String pesel;
    private Date dateOfBirth;
    private Contract[] contracts = new Contract[10];

    public IndividualCustomer(String phoneNumber, String email, String password, String pesel, Date dateOfBirth) {
        super(phoneNumber, email, password);
        this.pesel = pesel;
        this.dateOfBirth = dateOfBirth;
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
