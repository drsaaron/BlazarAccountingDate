/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blazartech.blazaraccountingdate.data.access.impl.mongo;

import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author scott
 */
@Repository
public interface AccountingDateDataRepository extends CrudRepository<AccountingDateData, String> {

    /**
     * find the accounting date with a null end date, of which there should be
     * only 1
     *
     * @return
     */
    public AccountingDateData findByEndDateIsNull();

    /**
     * find a list of dates that have been end-dated
     *
     * @return
     */
    public List<AccountingDateData> findByEndDateIsNotNull();

    /**
     * find a list of dates with an effective date between the start and end dates 
     * or start date before the effective date and end date is null
     * @param effectiveDate
     * @return 
     */
    @Query("{ 'startDate': { '$lte': ?0 }, $or: [{ 'endDate': { '$gte': ?0 } }, { 'endDate': null } ] }")
    public List<AccountingDateData> findByEffectiveDate(Date effectiveDate);
}
