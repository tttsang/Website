package com.jking.computersite.exception;

import com.jking.computersite.enums.BaseEnums;
import com.jking.computersite.enums.UserEnums;

public class MyException extends RuntimeException {

    private Integer code;

    public MyException(BaseEnums baseEnums) {
        super(baseEnums.getMessage());
        this.code = baseEnums.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
