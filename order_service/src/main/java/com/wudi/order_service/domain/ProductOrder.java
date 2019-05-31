package com.wudi.order_service.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品订单实体类
 */
public class ProductOrder implements Serializable {

    private int id;
    //商品名
    private String productName;
    //订单流水号
    private String tradeNo;
    //商品价格
    private int price;
    private Date createTime;
    //用户ID
    private int userId;

    private String userName;

    public ProductOrder() {
    }

    public ProductOrder(int id, String productName, String tradeNo, int price, Date createTime, int userId, String userName) {
        this.id = id;
        this.productName = productName;
        this.tradeNo = tradeNo;
        this.price = price;
        this.createTime = createTime;
        this.userId = userId;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
