/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.blazaraccountingdate.data.access.impl.mongo;

import com.blazartech.blazaraccountingdate.data.AccountingDate;
import com.blazartech.blazaraccountingdate.data.AccountingDateDAL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author scott
 */
@Service
public class AccountingDateDALMongoImpl implements AccountingDateDAL {

    private static final Logger log = LoggerFactory.getLogger(AccountingDateDALMongoImpl.class);
    
    @Autowired
    private AccountingDateDataRepository accountingDateRepository;
    
    private AccountingDate buildAccountingDate(AccountingDateData acData) {
        AccountingDate date = new AccountingDate(acData.getId(), acData.getAccountingDate(), acData.getStartDate(), acData.getEndDate());
        return date;
    }

    @Override
    public AccountingDate getAccountingDate() {
        log.info("getting current accounting date");
        
        return buildAccountingDate(accountingDateRepository.findByEndDateIsNull());
    }

    @Override
    public AccountingDate getAccountingDate(Date timestamp) {
        log.info("finding accounting date for {}", timestamp);
        
        List<AccountingDateData> dates = accountingDateRepository.findByEffectiveDate(timestamp);
        
        // sanity check that only one date was found
        if (dates.size() > 1) {
            throw new IllegalStateException("multiple dates found for " + timestamp);
        }
        
        if (dates.isEmpty()) {
            return null;
        } else {
            return buildAccountingDate(dates.getFirst());
        }
    }

    @Override
    public AccountingDate addAccountingDate(Date effectiveTimestamp, LocalDate newAccountingDate) {
        log.info("adding new accounting date {} effective {}", newAccountingDate, effectiveTimestamp);
        
        // end date any existing dates.
        AccountingDateData activeDate = accountingDateRepository.findByEndDateIsNull();
        if (activeDate != null) {
            activeDate.setEndDate(effectiveTimestamp);
            accountingDateRepository.save(activeDate);
        }
                
        // save the new date
        AccountingDateData newDate = new AccountingDateData(null, effectiveTimestamp, null, newAccountingDate);
        newDate = accountingDateRepository.save(newDate);
        
        // done
        return buildAccountingDate(newDate);
    }

    
}
