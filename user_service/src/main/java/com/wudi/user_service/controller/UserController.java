package com.wudi.user_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.wudi.user_service.UserVo;
import com.wudi.user_service.auth.Auth;
import com.wudi.user_service.common.Constants;
import com.wudi.user_service.common.Result;
import com.wudi.user_service.common.ResultCode;
import com.wudi.user_service.entity.BaseUser;
import com.wudi.user_service.service.BaseUserService;
import com.wudi.user_service.util.RandomCodeUtil;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private BaseUserService baseUserService;

    @Autowired
    private Auth auth;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 验证码登录
     * @param phone
     * @param code
     * @return
     */
    @RequestMapping("/login")
    public Result doLogin(String phone, @NotNull String code){
        // step1 check code
        if (!code.equals("1234")){
            return Result.buildFailed(ResultCode.VALIDATE_CODE_ERROR);
        }
        // step2  查询是否存在这个用户，不存在新增
        BaseUser baseUser =  baseUserService.findByPhone(phone);
        if (baseUser == null){
            baseUser = new BaseUser();
            baseUser.setPhone(phone);
            baseUser.setUserName("YS"+RandomCodeUtil.getRandCodeWithMixed(10,true));
            int id = baseUserService.insert(baseUser);
            baseUser.setId(id);
        }
        // step3  生成token
        String token =  auth.makeToken();
        UserVo userVo = new UserVo();
        userVo.setToken(token);
        BeanUtils.copyProperties(baseUser,userVo);

        //step4讲token保存到缓存 TimeUnit
        redisTemplate.opsForHash().put(Constants.BASE_USER_LOGIN_MAP,userVo.getToken(),JSONObject.toJSONString(userVo));
        // step5  返回用户信息
        return Result.buildSuccess(userVo);
    }


    /**
     * TODO 拦截器或者过滤器检查用户是否存在
     * 获取用户信息
     * @param token
     * @return
     */
    @RequestMapping("/getUserInfo")
    public Result getUserInfo(String token){

        String jsonStr = (String) redisTemplate.opsForHash().get(Constants.BASE_USER_LOGIN_MAP,token);
        UserVo userVo = JSONObject.parseObject(jsonStr,UserVo.class);
        return Result.buildSuccess(userVo);

    }

}
