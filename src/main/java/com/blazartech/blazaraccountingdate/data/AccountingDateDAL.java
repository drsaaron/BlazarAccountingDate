/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blazartech.blazaraccountingdate.data;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author scott
 */
public interface AccountingDateDAL {
    
    /**
     * get the accounting date as of now
     * @return 
     */
    public default AccountingDate getAccountingDate() { return getAccountingDate(new Date()); }
    
    /**
     * get the accounting date valid as of the given timestamp
     * @param timestamp
     * @return 
     */
    public AccountingDate getAccountingDate(Date timestamp);
    
    /**
     * add a new accounting date.  Any currently active accounting dates will
     * be end dated and the new one added.
     * 
     * @param effectiveTimestamp the starting timestamp of the new date
     * @param newAccountingDate the accounting date
     * @return 
     */
    public AccountingDate addAccountingDate(Date effectiveTimestamp, LocalDate newAccountingDate);
}
