package com.wudi.order_service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    private static  final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * josn 字符串转JsonNode对象的方法
     * @param str
     * @return
     */
    public  static JsonNode str2JsonNode(String str){
        try {
            return  objectMapper.readTree(str);
        } catch (IOException e) {
            return  null;
        }
    }
}
