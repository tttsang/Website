package com.jking.computersite.exception;

import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.exception.MyException;
import com.jking.computersite.utils.ResultVOUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class MyExceptionHandler {

    //拦截登录异常
    @ExceptionHandler(value = MyException.class)
    public ResultVO handlerLoginException(MyException e){
        return ResultVOUtil.fail(e.getCode(),e.getMessage());
    }

}
