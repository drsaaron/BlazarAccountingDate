/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.blazaraccountingdate.data;

import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component
@Slf4j
public class DetermineCurrentAccountingDateImpl implements DetermineCurrentAccountingDate {

    @Override
    public LocalDate currentAccountingDate() {
        /* this should implement rule to handle weekends, time of day, etc.  But 
           hey, it's just a toy
        */
        return LocalDate.now();
    }
    
}
