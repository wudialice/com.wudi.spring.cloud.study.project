package com.wudi.order_service.service.impl;

import com.wudi.order_service.domain.ProductOrder;
import com.wudi.order_service.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {
   @Autowired
    private RestTemplate restTemplate;


/*    @Autowired
    private LoadBalancerClient loadBalancer;*/

    @Override
    public ProductOrder save(int userId, int productId) {

        //获取商品详情
        Map<String,Object> productMap = restTemplate.getForObject("http://PRODUCT-SERVICE//api/vi/product/find?id="+productId, Map.class);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(productMap.get("name").toString());
        productOrder.setPrice(Integer.parseInt(productMap.get("price").toString()));
        return productOrder;
    }

    /**
     * Ribbon调用的第二种方式
     */
/*    public  void doStuff(){
        int productId = 1;
        ServiceInstance instance = loadBalancer.choose("PRODUCT-SERVICE");
        String url = String.format("http://%s:%s/api/vi/product/find?id="+productId,instance.getHost(),instance.getPort());
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> productMap = restTemplate.getForObject(url, Map.class);
    }*/
}
