package com.jking.computersite.controller;

import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.service.EsSearchService;
import com.jking.computersite.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SearchController {

    @Autowired
    private EsSearchService searchService;

    @ResponseBody
    @GetMapping("/search")
    public ResultVO search(String keyword,
                           @RequestParam(required = false,defaultValue = "1") int currentPage,
                           @RequestParam(required = false,defaultValue = "10") int pageSize,
                           @RequestParam(required = false, defaultValue = "200") int wordSize){

        return ResultVOUtil.success(searchService.search(keyword,currentPage,pageSize,wordSize));
    }

}
