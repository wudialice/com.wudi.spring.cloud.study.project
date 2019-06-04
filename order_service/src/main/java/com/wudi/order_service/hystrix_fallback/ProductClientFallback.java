package com.wudi.order_service.hystrix_fallback;

import com.wudi.order_service.feign.ProductClient;
import org.springframework.stereotype.Component;

/**
 * 增对商品服务错误，做降级处理
 */
@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public String findById(int id) {
        System.out.println("feign 调用 product-service失败");
        return null;
    }
}
