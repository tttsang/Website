package com.jking.computersite.enums;

public class BaseEnums {

    private Integer code;

    private String message;

    BaseEnums(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
