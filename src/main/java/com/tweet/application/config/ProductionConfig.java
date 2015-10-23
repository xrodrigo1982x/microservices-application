package com.tweet.application.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import com.tweet.application.ApplicationMain;
import com.tweet.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = UserRepository.class)
@EnableCaching
@EnableZuulProxy
@DefaultProfile
public class ProductionConfig {

    @Value("${hazelcast.endpoint:localhost:5701}")
    private String hazelcastEndpoint;

    @Bean
    public CacheManager cacheManager(){
        ClientConfig config = new ClientConfig();
        config.getNetworkConfig().addAddress(hazelcastEndpoint);
        HazelcastInstance client = HazelcastClient.newHazelcastClient(config);
        return new HazelcastCacheManager(client);
    }

}
