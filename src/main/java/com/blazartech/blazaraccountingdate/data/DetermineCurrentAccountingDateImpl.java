/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.blazaraccountingdate.data;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component
public class DetermineCurrentAccountingDateImpl implements DetermineCurrentAccountingDate {

    private static final Logger log = LoggerFactory.getLogger(DetermineCurrentAccountingDateImpl.class);
    
    @Override
    public LocalDate currentAccountingDate() {
        /* this should implement rule to handle weekends, time of day, etc.  But 
           hey, it's just a toy
        */
        return LocalDate.now();
    }
    
}
