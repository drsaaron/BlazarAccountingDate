/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.blazaraccountingdate;

import com.blazartech.blazaraccountingdate.data.AccountingDate;
import com.blazartech.blazaraccountingdate.data.AccountingDateDAL;
import com.blazartech.blazaraccountingdate.data.DetermineCurrentAccountingDate;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * initialize the data on startup
 * @author scott
 */
@Component
@Order(1)
public class AccountingDateStartupInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AccountingDateStartupInitializer.class);
    
    @Autowired
    private AccountingDateDAL dal;
    
    @Autowired
    private DetermineCurrentAccountingDate determineAccountingDate;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("adding a new accounting date to ensure there's something there");
        AccountingDate newDate = dal.addAccountingDate(new Date(), determineAccountingDate.currentAccountingDate());
        log.info("accounting date {} added", newDate);
    }
    
}
