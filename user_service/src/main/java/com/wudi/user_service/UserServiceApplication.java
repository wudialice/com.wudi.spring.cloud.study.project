package com.wudi.user_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

//仪表盘  监控地址 http://localhost:8781/hystrix
@EnableHystrixDashboard
//Hystrix熔断降级
@EnableCircuitBreaker
//使用Feign负载策略
@EnableFeignClients
@SpringBootApplication
@MapperScan("com.wudi.user_service.dao")
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
