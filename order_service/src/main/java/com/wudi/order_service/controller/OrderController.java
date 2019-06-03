package com.wudi.order_service.controller;

import com.wudi.order_service.domain.ProductOrder;
import com.wudi.order_service.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    /**
     *负载均衡实现方式用的ribbon
     */
    @Autowired
    private ProductOrderService productOrderService;


    /**
     *负载均衡实现方式用的Feign
     */
    @Autowired
    private ProductOrderService useFeignProductService;

    @RequestMapping("/save")
    public Object save(@RequestParam("user_id") int userId,
                       @RequestParam("product_id") int productId
                       ){
        return productOrderService.save(userId,productId);
    }


    @RequestMapping("/saveF")
    public Object saveF(@RequestParam("user_id") int userId,
                       @RequestParam("product_id") int productId
    ){
        return useFeignProductService.save(userId,productId);
    }
}
