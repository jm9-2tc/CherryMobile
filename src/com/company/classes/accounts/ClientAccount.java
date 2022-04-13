package com.company.classes.accounts;

import com.company.classes.accounts.base.Account;
import com.company.classes.contracts.base.Contract;

public class ClientAccount extends Account {
    private Contract[] contracts = new Contract[10];

    public ClientAccount(int id) {
        super(id);
    }

    public Contract[] getContracts() {
        return contracts;
    }

    public void setContracts(Contract[] contracts) {
        this.contracts = contracts;
    }
}
