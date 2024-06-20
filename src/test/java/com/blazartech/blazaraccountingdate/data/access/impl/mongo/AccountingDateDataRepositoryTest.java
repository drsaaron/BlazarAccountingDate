/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.blazartech.blazaraccountingdate.data.access.impl.mongo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
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
public class AccountingDateDataRepositoryTest {

    @Autowired
    private AccountingDateDataRepository instance;

    public AccountingDateDataRepositoryTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    private Date findOneMonthAgo(Date now) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.MONTH, -1);
        Date result = cal.getTime();
        return result;
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

    @BeforeEach
    public void setUp() {

        instance.deleteAll();

        Date startDate = parseDate("2022-01-01");
        Date endDate = parseDate("2022-12-31");

        AccountingDateData ac1 = new AccountingDateData(null, startDate, endDate, ACCOUNTING_DATE_ENDED);
        instance.save(ac1);

        AccountingDateData ac2 = new AccountingDateData(null, parseDate("2023-01-01"), null, ACCOUNTING_DATE_ACTIVE);
        instance.save(ac2);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAccountingDate method, of class AccountingDateDALMongoImpl.
     */
    @Test
    public void testFindByEndDateIsNull() {
        log.info("findByEndDateIsNull");

        LocalDate expResult = ACCOUNTING_DATE_ACTIVE;
        AccountingDateData result = instance.findByEndDateIsNull();
        log.info("result = {}", result);

        assertNotNull(result);
        assertEquals(expResult, result.getAccountingDate());
    }

    @Test
    public void testFindByEndDateIsNotNull() {
        log.info("findByEndDateIsNotNull");

        LocalDate expResult = ACCOUNTING_DATE_ENDED;
        List<AccountingDateData> result = instance.findByEndDateIsNotNull();
        log.info("result = {}", result);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(expResult, result.getFirst().getAccountingDate());
    }

    @Test
    public void testFindByEffectiveDateBetween() {
        log.info("findByEffectiveDateBetween");
        
        Date effectiveDate = findOneMonthAgo(parseDate("2022-12-31"));
        List<AccountingDateData> result = instance.findByEffectiveDate(effectiveDate);
        log.info("result, ended: {}", result);
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ACCOUNTING_DATE_ENDED, result.getFirst().getAccountingDate());
        
        effectiveDate = parseDate("2023-06-10");
        result = instance.findByEffectiveDate(effectiveDate);
        log.info("result, active: {}", result);
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ACCOUNTING_DATE_ACTIVE, result.getFirst().getAccountingDate());
        
        effectiveDate = parseDate("1998-01-01");
        result = instance.findByEffectiveDate(effectiveDate);
        log.info("result, way old: {}", result);
        
        assertNotNull(result);
        assertEquals(0, result.size());
    }
}