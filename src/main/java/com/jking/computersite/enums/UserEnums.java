package com.jking.computersite.enums;

public interface UserEnums {

    BaseEnums NOT_USER = new BaseEnums(1, "账号或密码为空");
    BaseEnums ID_NOT_FOUND = new BaseEnums(2, "账号不存在");
    BaseEnums PASSWORD_ERROR = new BaseEnums(3, "密码错误");
    BaseEnums LOGIN_ERROR = new BaseEnums(4,"尚未登录");

}
