package com.jking.computersite.controller;

import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.service.NewsService;
import com.jking.computersite.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/index")
@CrossOrigin
public class IndexController {
    @Autowired
    private NewsService newsService;

    @ResponseBody
    @GetMapping("/activityShow")
    public ResultVO Show() {
        return ResultVOUtil.success(newsService.Show());
    }

    @ResponseBody
    @GetMapping("/")
    public ResultVO Index() {
        return ResultVOUtil.success(newsService.Index());
    }

    @ResponseBody
    @GetMapping("/column")
    public  ResultVO Colunmn(@RequestParam int id,@RequestParam int p)
    {
        return ResultVOUtil.success(newsService.Column(id,p,15));
    }

    @ResponseBody
    @GetMapping("/article")
    public  ResultVO Article(@RequestParam Integer id)
    {
        return ResultVOUtil.success(newsService.Article(id));
    }
}
