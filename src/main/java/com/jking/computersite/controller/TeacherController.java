package com.jking.computersite.controller;

import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.service.TeacherService;
import com.jking.computersite.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("/teacherIntroduction")
    @ResponseBody
    public ResultVO select(@RequestParam Integer id)
    {
        return ResultVOUtil.success(teacherService.select(id));
    }
}
