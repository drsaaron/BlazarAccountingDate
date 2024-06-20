/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.blazartech.blazaraccountingdate.data.access.impl.mongo;

import com.blazartech.blazaraccountingdate.data.AccountingDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author scott
 */
@ExtendWith(SpringExtension.class)
@DataMongoTest
@PropertySource("classpath:mongo-test.properties")
@Slf4j
public class AccountingDateDALMongoImplTest {
    
    @TestConfiguration
    public static class AccountingDateDALMongoImplTestConfiguration {
        
        @Bean
        public AccountingDateDALMongoImpl instance() {
            return new AccountingDateDALMongoImpl();
        }
    }
    
    @Autowired
    private AccountingDateDALMongoImpl instance;
    
    @Autowired
    private AccountingDateDataRepository repo;
    
    public AccountingDateDALMongoImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
        repo.deleteAll();

        Date startDate = parseDate("2022-01-01");
        Date endDate = parseDate("2022-12-31");

        AccountingDateData ac1 = new AccountingDateData(null, startDate, endDate, ACCOUNTING_DATE_ENDED);
        repo.save(ac1);

        AccountingDateData ac2 = new AccountingDateData(null, parseDate("2023-01-01"), null, ACCOUNTING_DATE_ACTIVE);
        repo.save(ac2);
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    private Date parseDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException("error parsing date: " + ex.getMessage(), ex);
        }
    }

    private static final LocalDate ACCOUNTING_DATE_ENDED = LocalDate.parse("2022-06-01");
    private static final LocalDate ACCOUNTING_DATE_ACTIVE = LocalDate.parse("2023-06-01");

    /**
     * Test of getAccountingDate method, of class AccountingDateDALMongoImpl.
     */
    @Test
    public void testGetAccountingDate() {
        log.info("getAccountingDate");
        
        AccountingDate result = instance.getAccountingDate();
        LocalDate expResult = ACCOUNTING_DATE_ACTIVE;
        
        assertEquals(expResult, result.getAccountingDate());
    }

    /**
     * Test of getAccountingDate method, of class AccountingDateDALMongoImpl.
     */
    @Test
    public void testGetAccountingDate_Date() {
        log.info("getAccountingDate");
        
        Date timestamp = parseDate("2022-06-15");
        AccountingDate result = instance.getAccountingDate(timestamp);
        assertEquals(ACCOUNTING_DATE_ENDED, result.getAccountingDate());
    }
    
    @Test
    public void testAddAccountingDate() {
        log.info("addAccountingDate");
        
        // get the current active date
        AccountingDate currentDate = instance.getAccountingDate();
        assertNotNull(currentDate);
        assertNull(currentDate.getEndTime());
        
        // create a new date and save it.
        String newDateString = "2025-01-01";
        AccountingDate newDate = instance.addAccountingDate(parseDate(newDateString), LocalDate.parse(newDateString));
        
        // validate that the new date has a null end date and an ID
        assertNotNull(newDate.getId());
        assertNull(newDate.getEndTime());
        
        // re-retrieve the old date and validate that it's end-dated.
        Optional<AccountingDateData> oldCurrentDate = repo.findById(currentDate.getId());
        assertNotNull(oldCurrentDate);
        assertTrue(oldCurrentDate.isPresent());
        assertNotNull(oldCurrentDate.get().getEndDate());
        assertEquals(newDate.getStartTime(), oldCurrentDate.get().getEndDate());
    }
}
