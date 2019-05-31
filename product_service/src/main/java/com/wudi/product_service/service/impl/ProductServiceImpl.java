package com.wudi.product_service.service.impl;

import com.wudi.product_service.domain.Product;
import com.wudi.product_service.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private static Map<Integer,Product> daoMap = new HashMap<>();


    static {
        Product product1 = new Product(1,"iphonex",6000,10);
        Product product2 = new Product(2,"冰箱",10000,100);
        Product product3 = new Product(3,"洗衣机",899,10);
        Product product4 = new Product(4,"电视机",10000,10);
        Product product5 = new Product(5,"电饭煲",200,10);
        Product product6 = new Product(6,"微波炉",1200,10);
        Product product7 = new Product(7,"烤箱",398,10);
        Product product8 = new Product(8,"空调",2000,10);
        Product product9 = new Product(9,"电扇",200,100);
        Product product10 = new Product(10,"热水器",5000,20);


        daoMap.put(product1.getId(),product1);
        daoMap.put(product2.getId(),product2);
        daoMap.put(product3.getId(),product3);
        daoMap.put(product4.getId(),product4);
        daoMap.put(product5.getId(),product5);
        daoMap.put(product6.getId(),product6);
        daoMap.put(product7.getId(),product7);
        daoMap.put(product8.getId(),product8);
        daoMap.put(product9.getId(),product9);
        daoMap.put(product10.getId(),product10);

    }
    @Override
    public List<Product> listProduct() {
        Collection<Product> collection = daoMap.values();
        List<Product> productList = new ArrayList<>(collection);
        return productList;
    }

    @Override
    public Product findById(int id) {
        return daoMap.get(id);
    }
}
