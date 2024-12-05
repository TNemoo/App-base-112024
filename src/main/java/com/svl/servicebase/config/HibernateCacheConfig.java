package com.svl.servicebase.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.hibernate.RedissonRegionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

//
//@Configuration
//public class HibernateCacheConfig {
//
//    @Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://localhost:6379")
//                .setTimeout(3000)
//                .setRetryAttempts(3)
//                .setRetryInterval(1500)
//                .setConnectionPoolSize(64)
//                .setConnectionMinimumIdleSize(24)
//                .setSubscriptionConnectionPoolSize(50)
//                .setSubscriptionConnectionMinimumIdleSize(1);
//        return Redisson.create(config);
//    }
//}
//    @Bean
//    public RedissonRegionFactory hibernateCacheRegionFactory() {
//        RedissonRegionFactory redissonRegionFactory = new RedissonRegionFactory();
//        return redissonRegionFactory;
//    }

//    @Bean
//    @DependsOn("redissonClient")
//    public RedissonRegionFactory redissonRegionFactory() {
//        return new RedissonRegionFactory(redissonClient());
//    }
    /* Для настройки через бин, а вместо yml */
//    @Bean
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        return Redisson.create(config);
//    }
