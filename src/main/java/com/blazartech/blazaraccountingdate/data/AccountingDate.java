/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.blazaraccountingdate.data;

import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author scott
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountingDate {
    
    private String id;
    private LocalDate accountingDate;
    private Date startTime;
    private Date endTime;
}
