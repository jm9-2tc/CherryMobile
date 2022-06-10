package com.company.controllers;

import com.company.controllers.base.InheritanceController;
import com.company.data.DatabaseManager;
import com.company.exceptions.ObjectNotSavedException;
import com.company.exceptions.TooManyContractsException;
import com.company.model.contract.PostPaidContract;
import com.company.model.contract.PostPaidPrices;
import com.company.model.contract.PrePaidPrices;
import com.company.model.contract.base.Contract;
import com.company.model.contract.PrePaidContract;
import com.company.model.customer.IndividualCustomer;
import com.company.model.customer.base.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractController extends InheritanceController<Contract> {
    public ContractController(DatabaseManager database) {
        super(database);
    }

    public List<Contract> loadCustomerContracts(int customerId) {
        List<Contract> contracts = new ArrayList<>();
        ResultSet result = database.executeQuery("SELECT id FROM contract WHERE customer_id = " + customerId + ";");

        try {
            while (result.next()) {
                contracts.add(load(result.getInt(1)));
            }
        } catch (SQLException ignored) {}
        return contracts;
    }

    public void assignToCustomer(Contract contract, Customer customer) {
        if (contract.getId() == null) {
            throw new ObjectNotSavedException("contract");
        }

        if (customer.getId() == null) {
            throw new ObjectNotSavedException("customer");
        }

        List<Contract> contractList = customer.getContracts();

        if (customer instanceof IndividualCustomer && contractList.size() == IndividualCustomer.MAX_CONTRACTS) {
            throw new TooManyContractsException();
        }

        contractList.add(contract);
        database.execute("UPDATE contract SET customer_id = " + customer.getId() + " WHERE id = " + contract.getId() + ";");
    }



    public void savePrePaid(PrePaidContract contract) {
        int contractId = saveBase(contract);
        database.execute("INSERT INTO pre_paid_contract VALUES ("
                + contractId + ", "
                + contract.getSmsCount() + ", "
                + contract.getSmsCount() + ", "
                + contract.getInternetMegabytesCount() + ", "
                + contract.getContractMoney() + ")"
        );
        savePrePaidPrices(contractId, contract.getPrices());
    }

    public void savePostPaid(PostPaidContract contract) {
        int contractId = saveBase(contract);
        database.execute("INSERT INTO post_paid_contract VALUES ("
                + contractId + ", "
                + contract.getSmsSent() + ", "
                + contract.getPhoneMinutesUsed() + ", "
                + contract.getInternetMegabytesUsed() + ")"
        );
        savePostPaidPrices(contractId, contract.getPrices());
    }

    private void savePrePaidPrices(int id, PrePaidPrices prices) {
        database.execute("INSERT INTO pre_paid_prices VALUES ("
                + id + ", "
                + prices.getPhoneMinutePrice() + ", "
                + prices.getSmsPrice() + ", "
                + prices.getInternetMegabytePrice() + ")"
        );
    }

    private void savePostPaidPrices(int id, PostPaidPrices prices) {
        database.execute("INSERT INTO post_paid_prices VALUES ("
                + id + ", "
                + prices.getContractPrice() + ", "
                + prices.getSmsCountInPrice() + ", "
                + prices.getPhoneMinutesCountInPrice() + ", "
                + prices.getInternetMegabytesCountInPrice() + ", "
                + prices.getSmsAboveContractPrice() + ", "
                + prices.getPhoneMinuteAboveContractPrice() + ", "
                + prices.getInternetMegabyteAboveContractPrice() + ")"
        );
    }

    @Override
    protected int saveBase(Contract object) {
        database.execute("INSERT INTO contract VALUES (NULL, NULL, '"
                + object.getCreationDate() + "', '"
                + object.getEndingDate() + "')"
        );

        int contractId = -1;
        try {
            ResultSet queryResult = database.executeQuery("SELECT MAX(id) AS id FROM contract;");
            contractId = queryResult.getInt(1);
            object.setId(contractId);
            queryResult.close();
        } catch (SQLException ignored) {}
        return contractId;
    }

    public PrePaidContract loadPrePaid(int id) {
        Contract base = loadBase(id);

        if (base != null) {
            PrePaidContract contract = null;
            ResultSet result = database.executeQuery("SELECT * FROM pre_paid_contract WHERE contract_ptr_id = " + id + ";");

            try {
                contract = new PrePaidContract(
                        base.getCreationDate(),
                        base.getEndingDate(),
                        result.getInt(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getInt(5),
                        loadPrePaidPrices(id)
                );
                contract.setId(result.getInt(1));
            } catch (SQLException ignored) {}

            return contract;
        } else {
            return null;
        }
    }

    public PostPaidContract loadPostPaid(int id) {
        Contract base = loadBase(id);

        if (base != null) {
            PostPaidContract contract = null;
            ResultSet result = database.executeQuery("SELECT * FROM post_paid_contract WHERE contract_ptr_id = " + id + ";");

            try {
                contract = new PostPaidContract(
                        base.getCreationDate(),
                        base.getEndingDate(),
                        result.getInt(2),
                        result.getInt(3),
                        result.getInt(4),
                        loadPostPaidPrices(id)
                );
                contract.setId(result.getInt(1));
            } catch (SQLException ignored) {}

            return contract;
        } else {
            return null;
        }
    }

    private PrePaidPrices loadPrePaidPrices(int id) {
        try {
            ResultSet result = database.executeQuery("SELECT * FROM pre_paid_prices WHERE contract_ptr_id = " + id + ";");

            return new PrePaidPrices(
                    result.getInt(2),
                    result.getInt(3),
                    result.getInt(4)
            );
        } catch (SQLException ignored) {}
        return null;
    }

    private PostPaidPrices loadPostPaidPrices(int id) {
        try {
            ResultSet result = database.executeQuery("SELECT * FROM post_paid_prices WHERE contract_ptr_id = " + id + ";");

            return new PostPaidPrices(
                    result.getInt(2),
                    result.getInt(3),
                    result.getInt(4),
                    result.getInt(5),
                    result.getInt(6),
                    result.getInt(7),
                    result.getInt(8)
            );
        } catch (SQLException ignored) {}
        return null;
    }

    public Contract load(int id) {
        ResultSet prePaid = database.executeQuery("SELECT contract_ptr_id FROM pre_paid_contract WHERE contract_ptr_id = " + id + ";");
        ResultSet postPaid = database.executeQuery("SELECT contract_ptr_id FROM post_paid_contract WHERE contract_ptr_id = " + id + ";");

        try {
            if (prePaid.next()) {
                return loadPostPaid(id);
            } else if (postPaid.next()) {
                return loadPostPaid(id);
            }
        } catch (SQLException ignored) {}
        return null;
    }

    @Override
    protected Contract loadBase(int id) {
        ResultSet result = database.executeQuery("SELECT * FROM contract WHERE id = " + id + ";");
        Contract contract = null;
        try {
            contract = new Contract(
                    result.getDate(3),
                    result.getDate(4)
            );
            contract.setId(result.getInt(1));
        } catch (SQLException ignored) {}
        return contract;
    }
}
