package com.wudi.user_service.auth;

import com.wudi.user_service.util.MD5Encoder;
import com.wudi.user_service.util.UUidGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create by 高帅 2018/10/26
 */
@Component
public class Auth {

    @Autowired
    private UUidGenerate uidGenerate;

    public String makeToken() {  //checkException
        String token = String.valueOf(uidGenerate.nextId());
        //数据指纹   128位长   16个字节  md5
        return MD5Encoder.encode(token);
    }

}
