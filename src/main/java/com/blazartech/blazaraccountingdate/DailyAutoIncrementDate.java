/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.blazaraccountingdate;

import com.blazartech.blazaraccountingdate.data.AccountingDate;
import com.blazartech.blazaraccountingdate.data.AccountingDateDAL;
import com.blazartech.blazaraccountingdate.data.DetermineCurrentAccountingDate;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * a daily task to auto-increment the accounting date
 * @author scott
 */
@Component
@Slf4j
public class DailyAutoIncrementDate {
    
    @Autowired
    private AccountingDateDAL dal;
    
    @Autowired
    private DetermineCurrentAccountingDate determineAccountingDate;
    
    @Scheduled(cron = "${accountingDate.increment.schedule}")
    public void updateAccountingDate() {
        log.info("automatically updating date");
        
        AccountingDate newDate = dal.addAccountingDate(new Date(), determineAccountingDate.currentAccountingDate());
        log.info("accounting date {} added", newDate);
    }
}
