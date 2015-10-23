package com.tweet.application.config;

import com.mongodb.MongoClient;
import com.tweet.application.ApplicationMain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories(basePackageClasses = ApplicationMain.class)
public class MongoConfig {

    @Value("${mongo.host:localhost}")
    private String mongoHost;

    @Value("${mongo.port:27017}")
    private Integer monhoPort;

    @Value("${mongo.database:login}")
    private String mongoDatabase;

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        return new MongoTemplate(new MongoClient(mongoHost, monhoPort), mongoDatabase);
    }

}
