package com.jking.computersite.controller;

import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.service.ArticleService;
import com.jking.computersite.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("article")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @PostMapping("/")
    public ResultVO add(){

        return ResultVOUtil.success();
    }

    @ResponseBody
    @GetMapping("/{first}/{second}")
    public ResultVO getList(@PathVariable String first,
                        @PathVariable String second,
                        @RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "15") int pageSize
                        ){
        return ResultVOUtil.success(articleService.find(first,second,page-1,pageSize));
    }

    @ResponseBody
    @GetMapping("/indexInit")
    public ResultVO get(){
        Map<String, Object> map = new LinkedHashMap<>();

        return ResultVOUtil.success(articleService.init());
    }

    @ResponseBody
    @GetMapping("//{id}")
    public ResultVO getOne(@PathVariable String id){
        Map<String, Object> map = new LinkedHashMap<>();

        return ResultVOUtil.success(articleService.getArticle(id));
    }

}
