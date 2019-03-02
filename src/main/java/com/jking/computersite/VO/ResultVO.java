package com.jking.computersite.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.servlet.http.Cookie;

@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResultVO<T> {

    //错误码
    private Integer code;

    //提示信息
    private String message;

    //具体信息
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
