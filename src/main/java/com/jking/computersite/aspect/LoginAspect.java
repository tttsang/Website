package com.jking.computersite.aspect;

import com.jking.computersite.constant.CookieConstant;
import com.jking.computersite.constant.RedisConstant;
import com.jking.computersite.enums.UserEnums;
import com.jking.computersite.exception.MyException;
import com.jking.computersite.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. 要在状态码为200时才进入该页面
 */
@Aspect
@Component
@Slf4j
public class LoginAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Before("execution(public * com.jking.computersite.controller.*.*(..))")
    public void login(){

        //1.1 获取request和response对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        //1.2 处理所有origin的跨域
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Methods", "*");
        if (!StringUtils.isEmpty(origin)){
            response.setHeader("Access-Control-Allow-Origin", origin);
        }else {
            response.setHeader("Access-Control-Allow-Origin", "null");
        }

        //1.3 处理非简单请求的请求问题
        response.setHeader("Access-Control-Max-Age", "3600");

        //1.4 允许带有cookie的跨域
        response.setHeader("Access-Control-Allow-Credentials", "true");

        //1.5 允许所有请求头的跨域
        String headers = request.getHeader("Access-Control-Allow-Headers");
        if (!StringUtils.isEmpty(headers)){
            response.setHeader("Access-Control-Allow-Headers",headers);
        }

        //2. 拦截非GET的请求
        if(request.getMethod().equals("GET") || request.getServletPath().equals("/login")){
            return;
        }

        //3. 查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null){
            log.warn("【登录校验】Cookie中查不到token, 访问的ip为" + request.getRequestURI());
            throw new MyException(UserEnums.LOGIN_ERROR);
        }

        //4 去redis查找token
        String redis_token = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PRFIX,cookie.getValue()));
        if (StringUtils.isEmpty(redis_token)){
            log.warn("【登录校验】redis中查不到token, 访问的ip为" + request.getRequestURI());
            throw new MyException(UserEnums.LOGIN_ERROR);
        }

    }

}