package com.wudi.user_service.service;

import com.wudi.user_service.entity.BaseUser;
import org.springframework.transaction.annotation.Transactional;

public interface BaseUserService {
    BaseUser findByPhone(String phone);

    @Transactional(rollbackFor = Throwable.class)
    int insert(BaseUser baseUser);
}
