/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.blazaraccountingdate;

import com.blazartech.blazaraccountingdate.data.AccountingDate;
import com.blazartech.blazaraccountingdate.data.AccountingDateDAL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author scott
 */
@RestController
public class AccountingDateRestController {
    
    private static final Logger log = LoggerFactory.getLogger(AccountingDateRestController.class);
    
    @Autowired
    private AccountingDateDAL dal;
    
    @GetMapping("/v1")
    public AccountingDate getCurrentAccountingDate() {
        log.info("getting current accounting date");
        
        return dal.getAccountingDate();
    }
}
