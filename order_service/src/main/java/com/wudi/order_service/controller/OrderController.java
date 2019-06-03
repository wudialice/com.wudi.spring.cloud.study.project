package com.wudi.order_service.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wudi.order_service.domain.ProductOrder;
import com.wudi.order_service.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    @HystrixCommand(fallbackMethod = "saveOrFail")    public Object saveF(@RequestParam("user_id") int userId,
                                                                          @RequestParam("product_id") int productId
    ){
        Map<String,Object> data = new HashMap<>();
        data.put("code",0);
        data.put("data",useFeignProductService.save(userId,productId));
        return data;
    }

    private Object saveOrFail(int userId,int productId){
        Map<String,Object> msg = new HashMap<>();
        msg.put("code",-1);
        msg.put("msg","抢购的人数太多了，您被挤出来了，稍等重试");
        return  msg;
    }
}
