/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.blazaraccountingdate.data;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author scott
 */
public class AccountingDate {

    public AccountingDate() {
    }

    public AccountingDate(String id, LocalDate accountingDate, Date startTime, Date endTime) {
        this.id = id;
        this.accountingDate = accountingDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    private String id;
    private LocalDate accountingDate;
    private Date startTime;
    private Date endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(LocalDate accountingDate) {
        this.accountingDate = accountingDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    
}
