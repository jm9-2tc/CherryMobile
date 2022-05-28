package com.company.controllers;

import com.company.controllers.base.Controller;
import com.company.data.DatabaseManager;
import com.company.model.contract.base.Contract;
import com.company.model.contract.PrePaidContract;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContractController extends Controller<Contract> {
    protected ContractController(DatabaseManager database) {
        super(database);
    }

    public void saveContract(PrePaidContract contract) {

    }

    @Override
    protected int saveBase(Contract object) {
        database.execute("INSERT INTO customer VALUES (NULL, NULL)");
        int contractId = -1;
        try {
            ResultSet queryResult = database.executeQuery("SELECT id FROM contract ORDER BY id DESC LIMIT 1;");
            contractId = queryResult.getInt(1);
            queryResult.close();
        } catch (SQLException ignored) {}
        return contractId;
    }
}
