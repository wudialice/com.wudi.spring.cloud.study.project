package com.wudi.order_service.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wudi.order_service.domain.ProductOrder;
import com.wudi.order_service.service.ProductOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    /**
     *负载均衡实现方式用的ribbon
     */
    @Autowired
    private ProductOrderService productOrderService;
    @Autowired
    private StringRedisTemplate redisTemplate;


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
                                                                          @RequestParam("product_id") int productId,
                                                                          HttpServletRequest request
    ){
        String cookie =  request.getHeader("cookie");
        String token =  request.getHeader("token");

        System.out.println("cookie="+cookie);
        System.out.println("token="+token);

        Map<String,Object> data = new HashMap<>();
        data.put("code",0);
        data.put("data",useFeignProductService.save(userId,productId));
        return data;
    }

    private Object saveOrFail(int userId,int productId, HttpServletRequest request ){
        //监控报警 它在微服务中是必须的
        String saveOrderKey ="save-order";
        String sendValue = redisTemplate.opsForValue().get(saveOrderKey);

        /**
         * 监控报警——>短信通知的方式
         * 主线程直接返回（发送短信http请求耗费时间，在主线程里会阻塞一定时间）,所以一下通过子线程处理
         * new Thread子线程去发送短信给用户
         */
        new Thread( () ->{
            if (StringUtils.isBlank(sendValue)){
                System.out.println("紧急短信，用户下单失败，请查找原因,用户IP是："+request.getRemoteAddr() );
                //发送一个http请求，调用短信服务 TODO
                redisTemplate.opsForValue().set(saveOrderKey,"save-order-fail",20, TimeUnit.SECONDS);
            }else {
                System.out.println("已经发送过短信，20秒内不重复发送" );
            }
        }).start();

        Map<String,Object> msg = new HashMap<>();
        msg.put("code",-1);
        msg.put("msg","抢购的人数太多了，您被挤出来了，稍等重试");
        return  msg;
    }
}
