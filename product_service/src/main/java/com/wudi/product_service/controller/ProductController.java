package com.wudi.product_service.controller;

import com.wudi.product_service.domain.Product;
import com.wudi.product_service.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 动态的刷新配置 推送配置到客户端
 *  http://localhost:8773/actuator/bus-refresh
 */
@RefreshScope
@RestController
@RequestMapping("/api/vi/product")
public class ProductController {

    @Value("${server.port}")
    private String port;

    @Value("${env}")
    private String env;

    @Autowired
    private ProductService productService;

    /**
     * 获取所有商品列表
     * @return
     */
    @RequestMapping("/list")
    public Object list(){
        return  productService.listProduct();
    }

    /**
     * 根据ID查询商品详情
     * @param id
     * @return
     */
    @RequestMapping("/find")
    public Object findById(@RequestParam("id") int id){
        Product product = productService.findById(id);
      Product result = new Product();
      BeanUtils.copyProperties(product,result);
      result.setName( result.getName() +"data from port="+port +" env="+ env);
      return result;
    }
}
