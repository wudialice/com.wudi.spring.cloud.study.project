package com.wudi.order_service.service;

import com.wudi.order_service.domain.ProductOrder;

/**
 * 订单业务接口
 */
public interface ProductOrderService {

    ProductOrder save(int userId,int productId);
}
