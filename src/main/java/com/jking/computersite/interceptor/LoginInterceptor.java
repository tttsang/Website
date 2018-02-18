package com.jking.computersite.interceptor;

import com.jking.computersite.constant.CookieConstant;
import com.jking.computersite.constant.RedisConstant;
import com.jking.computersite.enums.UserEnums;
import com.jking.computersite.exception.MyException;
import com.jking.computersite.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //1. 得到HttpServletRequest对象
        response.setHeader("Access-Control-Allow-Origin", "null");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "Set-Cookie");

        //2.1 拦截非GET的请求
        if(request.getMethod().equals("GET") || request.getServletPath().equals("/login")){
            return true;
        }

        //2.2 拦截admin的请求路径
//        if(request.getServletPath())
        System.out.println(request.getRequestURI());
        System.out.println(request.getContextPath());

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

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}
