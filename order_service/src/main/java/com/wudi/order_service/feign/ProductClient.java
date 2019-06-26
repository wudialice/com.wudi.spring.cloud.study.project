package com.wudi.order_service.feign;

import com.wudi.order_service.hystrix_fallback.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用客户端接口，fallback调用失败的降级类ProductClientFallback
 */
@FeignClient(name = "product-service",fallback = ProductClientFallback.class)
public interface ProductClient {
    @GetMapping("/api/vi/product/find")
    String findById(@RequestParam(value = "id") int id);
}
