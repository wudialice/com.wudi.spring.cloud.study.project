package com.wudi.user_service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wudi.user_service.entity.BaseUser;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVo extends BaseUser implements Serializable {
    private static final long serialVersionUID = 1990036540404638677L;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
