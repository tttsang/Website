package com.jking.computersite.enums;

public interface UserEnums{

    BaseEnums PASSWORD_ERROR = new BaseEnums(1, "密码错误");
    BaseEnums LOGIN_ERROR = new BaseEnums(2,"尚未登录");
    BaseEnums PASSWORD_NULL = new BaseEnums(3, "密码不能为空");
    BaseEnums PASSWORD_NOT_EQUAL = new BaseEnums(4, "两次密码不一致");

}
