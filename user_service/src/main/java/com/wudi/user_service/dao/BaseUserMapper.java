package com.wudi.user_service.dao;

import com.wudi.user_service.entity.BaseUser;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseUser record);

    int insertSelective(BaseUser record);

    BaseUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseUser record);

    int updateByPrimaryKey(BaseUser record);

    BaseUser findByPhone(String phone);
}