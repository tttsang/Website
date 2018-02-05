package com.jking.computersite.utils;

import com.jking.computersite.VO.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        return resultVO;
    }

    public static ResultVO fail(Integer code,String meassage){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(meassage);
        return resultVO;
    }

}
