package com.wudi.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//仪表盘  监控地址 http://localhost:8781/hystrix
@EnableHystrixDashboard
//Hystrix熔断降级
@EnableCircuitBreaker
//使用Feign负载策略
@EnableFeignClients
@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    /**
     *Ribbon负载均衡 加入这个用来做负载均衡(客户端负载均衡)
     * @return
     */
    @Bean
    /**
     * 采用默认的负载均衡策略
     */
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
