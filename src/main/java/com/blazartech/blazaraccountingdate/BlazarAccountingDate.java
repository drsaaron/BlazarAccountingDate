/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.blazartech.blazaraccountingdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author scott
 */
@SpringBootApplication
@EnableScheduling
public class BlazarAccountingDate {

    public static void main(String[] args) {
        SpringApplication.run(BlazarAccountingDate.class, args);
    }
}
