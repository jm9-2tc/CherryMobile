package com.company.generators;

import com.company.data.DatabaseManager;
import com.company.exceptions.ObjectNotSavedException;
import com.company.model.customer.base.Customer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

public class InvoiceGenerator {
    private static final String sqlFilePath = "src\\sql\\invoice.sql";
    private static final String invoiceQuery;

    private final DatabaseManager database;

    static {
        String sql = "";
        try {
            byte[] bytes = new FileInputStream(sqlFilePath).readAllBytes();
            sql = new String(bytes);
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
        invoiceQuery = sql;
    }

    public InvoiceGenerator(DatabaseManager database) {
        this.database = database;
    }

    public String generate(Customer customer) {
        Integer customerId = customer.getId();

        if (customerId == null) {
            throw new ObjectNotSavedException("customer");
        }

        String query = invoiceQuery.replaceFirst("CUSTOMER_ID", customerId.toString());
        ResultSet prices = database.executeQuery(query);

        StringBuilder result = new StringBuilder();
        int total = 0;

        try {
            while (prices.next()) {
                int contractPrice = prices.getInt(2);
                int smsPrice = prices.getInt(3);
                int phonePrice = prices.getInt(4);
                int internetPrice = prices.getInt(5);

                result.append("\nContract no. ").append(prices.getInt(1)).append(" - ").append(formatPrice(contractPrice + smsPrice + phonePrice + internetPrice)).append(':');
                result.append("\n\tbase: ").append(formatPrice(contractPrice));
                result.append("\n\tadditional SMS: ").append(formatPrice(smsPrice));
                result.append("\n\tadditional phone: ").append(formatPrice(phonePrice));
                result.append("\n\tadditional internet: ").append(formatPrice(internetPrice)).append('\n');
                total += (contractPrice + smsPrice + phonePrice + internetPrice);
            }
            result.append("_".repeat(32));
            result.append("\nTOTAL: ").append(formatPrice(total));
        } catch (SQLException exception) {
            System.out.println("cannot generate invoice for customer with id " + customerId);
        }
        return result.toString();
    }

    private String formatPrice(int price) {
        BigDecimal decimal = BigDecimal.valueOf(price).movePointLeft(4);
        return NumberFormat.getCurrencyInstance().format(decimal);
    }
}
