package com.jking.computersite.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResultVO<T> {

    //错误码
    private Integer code;

    //提示信息
    private String message;

    //具体信息
    private T data;
}
