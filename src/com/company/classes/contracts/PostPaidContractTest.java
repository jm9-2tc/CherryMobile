package com.company.classes.contracts;

import com.company.classes.contracts.base.PostPaidContract;

import java.time.LocalDate;

public class PostPaidContractTest extends PostPaidContract {
    public PostPaidContractTest(){
        setEndingDate(LocalDate.now().plusDays(LocalDate.now().lengthOfMonth()));
        activateContract();
    }
}
