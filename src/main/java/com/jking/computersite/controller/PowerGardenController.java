package com.jking.computersite.controller;

import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.entity.PowerGarden;
import com.jking.computersite.enums.CommonEnums;
import com.jking.computersite.exception.MyException;
import com.jking.computersite.service.PowerGardenSerivce;
import com.jking.computersite.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("powerGarden")
@CrossOrigin
public class PowerGardenController {

    @Autowired
    private PowerGardenSerivce powerGardenSerivce;

    @ResponseBody
    @GetMapping("/")
    public ResultVO getAll(){
        return ResultVOUtil.success(powerGardenSerivce.getAll());
    }

    @ResponseBody
    @PostMapping("/")
    public ResultVO add(PowerGarden powerGarden){
        System.out.println(powerGarden);
        if (StringUtils.isEmpty(powerGarden.getName()) || powerGarden.getMajor()==null){
            throw new MyException(CommonEnums.DATA_UNCOMPLETED);
        }
        powerGardenSerivce.add(powerGarden);
        return ResultVOUtil.success();
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id){
        powerGardenSerivce.delele(id);
        return ResultVOUtil.success();
    }
}
