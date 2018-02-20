package com.jking.computersite.enums;

public interface UserEnums{

    BaseEnums PASSWORD_ERROR = new BaseEnums(1, "密码错误");
    BaseEnums LOGIN_ERROR = new BaseEnums(2,"尚未登录");
    BaseEnums PASSWORD_NULL = new BaseEnums(3, "密码不能为空");

}
