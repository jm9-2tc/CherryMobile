package com.company.model.customer;

import com.company.model.contract.base.Contract;
import com.company.model.customer.base.Customer;

import java.util.Date;

public class IndividualCustomer extends Customer {
    private String firstName;
    private String lastName;
    private String pesel;
    private Date dateOfBirth;
    private Address address;
    private Contract[] contracts = new Contract[10];

    public IndividualCustomer(String phoneNumber, String email, String password, String firstName, String lastName, String pesel, Date dateOfBirth, Address address) {
        super(phoneNumber, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contract[] getContracts() {
        return contracts;
    }

    public void setContracts(Contract[] contracts) {
        this.contracts = contracts;
    }
}
