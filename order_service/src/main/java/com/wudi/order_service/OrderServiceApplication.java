package com.wudi.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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
