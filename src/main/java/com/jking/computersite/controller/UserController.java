package com.jking.computersite.controller;

import com.jking.computersite.Service.UserService;
import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.constant.CookieConstant;
import com.jking.computersite.constant.RedisConstant;
import com.jking.computersite.entity.User;
import com.jking.computersite.enums.UserEnums;
import com.jking.computersite.exception.MyException;
import com.jking.computersite.utils.CookieUtil;
import com.jking.computersite.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class UserController/* throws UserException*/{

    @Autowired
    private UserService userService;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("login")
    public ResultVO login(User user, HttpServletResponse response){
        //1. 判断账号密码是否正确
        userService.login(user);

        //2. 设置token到redis中
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PRFIX,token),user.getId(),expire, TimeUnit.SECONDS);

        //3. 将token设置到cookie中
        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);

        return ResultVOUtil.success();

    }

    @GetMapping("/logout")
    public ResultVO logout(HttpServletRequest request, HttpServletResponse response){
        //1. 从cookie中查询
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if (cookie != null){
            //2. 清除redis
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PRFIX,cookie.getValue()));
            //3. 清除cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
            return ResultVOUtil.success();
        }else {
            throw new MyException(UserEnums.LOGIN_ERROR);
        }

    }
}
