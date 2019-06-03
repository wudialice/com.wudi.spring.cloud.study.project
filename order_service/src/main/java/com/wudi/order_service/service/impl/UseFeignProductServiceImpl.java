package com.wudi.order_service.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.wudi.order_service.domain.ProductOrder;
import com.wudi.order_service.feign.ProductClient;
import com.wudi.order_service.service.ProductOrderService;
import com.wudi.order_service.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service(value = "useFeignProductService")
public class UseFeignProductServiceImpl implements ProductOrderService {

    @Autowired
   private ProductClient productClient;

    @Override
    public ProductOrder save(int userId, int productId) {
        String response =  productClient.findById(productId);

        JsonNode jsonNode = JsonUtil.str2JsonNode(response);
        //获取商品详情
        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));
        return productOrder;
    }
}
