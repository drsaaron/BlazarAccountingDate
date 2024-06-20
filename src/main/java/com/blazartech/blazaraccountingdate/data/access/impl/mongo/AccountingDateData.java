/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.blazaraccountingdate.data.access.impl.mongo;

import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author scott
 */
@Document(collection = "accounting_date")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountingDateData {
    
    @Id
    private String id;

    private Date startDate;
    private Date endDate;
    private LocalDate accountingDate;
}
