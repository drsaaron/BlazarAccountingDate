/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.blazaraccountingdate.data.access.impl.mongo;

import java.time.LocalDate;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author scott
 */
@Document(collection = "accounting_date")
public class AccountingDateData {

    public AccountingDateData() {
    }

    public AccountingDateData(String id, Date startDate, Date endDate, LocalDate accountingDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accountingDate = accountingDate;
    }
    
    @Id
    private String id;

    private Date startDate;
    private Date endDate;
    private LocalDate accountingDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public LocalDate getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(LocalDate accountingDate) {
        this.accountingDate = accountingDate;
    }
    
    
}
