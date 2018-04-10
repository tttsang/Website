package com.jking.computersite.controller;

import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.service.LeaderService;
import com.jking.computersite.utils.ResultVOUtil;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin

public class LeaderController {

    @Autowired
    LeaderService leaderService;

    @ResponseBody
    @GetMapping("/work")
    public ResultVO work()
    {
        return ResultVOUtil.success(leaderService.work());
    }

    @ResponseBody
    @GetMapping("/union")
    public  ResultVO union ()
    {
        return  ResultVOUtil.success(leaderService.union());
    }

    @ResponseBody
    @GetMapping("/institutionSetting")
    public  ResultVO insitution()
    {
        return  ResultVOUtil.success(leaderService.institution());
    }
}
