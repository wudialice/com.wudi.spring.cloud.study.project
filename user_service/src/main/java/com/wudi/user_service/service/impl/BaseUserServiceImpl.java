package com.wudi.user_service.service.impl;

import com.wudi.user_service.dao.BaseUserMapper;
import com.wudi.user_service.entity.BaseUser;
import com.wudi.user_service.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BaseUserServiceImpl implements BaseUserService {
    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    public int insert(BaseUser baseUser) {
        baseUser.setStatus(1);
        baseUser.setCreateTime(new Date());
        baseUser.setUpdateTime(new Date());
       return baseUserMapper.insert(baseUser);
    }

    @Override
    public BaseUser findByPhone(String phone) {
        return baseUserMapper.findByPhone(phone);
    }
}
