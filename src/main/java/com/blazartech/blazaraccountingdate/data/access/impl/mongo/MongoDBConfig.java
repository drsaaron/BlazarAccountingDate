/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.blazaraccountingdate.data.access.impl.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 *
 * @author scott
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.blazartech.blazaraccountingdate.data.access.impl.mongo")
@Slf4j
public class MongoDBConfig extends AbstractMongoClientConfiguration {
    
    @Value("${accountingDate.databaseName}")
    private String databaseName;
    
    @Override
    protected String getDatabaseName() {
        return databaseName;
    }
    
    @Value("${accountingDate.host}")
    private String hostName;
    
    @Value("${accountingDate.port}")
    private int port;
    
    @Override
    public MongoClient mongoClient() {
        log.info("creating mongo client");
        String url = "mongodb://" + hostName + ":" + port + "/" + databaseName + "?retryWrites=false";
        return MongoClients.create(url);
    }

}
