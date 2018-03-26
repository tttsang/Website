package com.jking.computersite.controller;

import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.constant.UploadConstant;
import com.jking.computersite.entity.news;
import com.jking.computersite.service.NewsService;
import com.jking.computersite.utils.FileUtil;
import com.jking.computersite.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/news")
@CrossOrigin
public class NewsController {

 @Autowired
    private NewsService newsService;

 @ResponseBody
@PostMapping("/")
   public ResultVO post(){
    return ResultVOUtil.success();
}

@ResponseBody
@PostMapping("/update/{id}")
  public  ResultVO update(@RequestParam(required = false)String title, @RequestParam(required = false)String author,
                          @RequestParam(required = false)String auditor, @RequestParam(required = false)String content,
                          @RequestParam(required = false)MultipartFile file,@RequestParam Integer id)
{
    news News_Sql=newsService.Find(id);
    if (file!=null && !file.isEmpty()){
        FileUtil.isImage(file);
        String filePath = News_Sql.getPictureUrl();
        FileUtil.deleteFile(UploadConstant.PUBLIC + filePath);
        FileUtil.saveFile(file, UploadConstant.PUBLIC + filePath);
        News_Sql.setPictureUrl(filePath);
    }
    news News=new news();
    News.setAuditor(auditor);
    News.setPictureUrl(News_Sql.getPictureUrl());
    News.setAuthor(author);
    News.setContent(content);
    News.setTitle(title);
    newsService.Update(News);
    return  ResultVOUtil.success();
}
@ResponseBody
@GetMapping("/{id}")
    public ResultVO find(@RequestParam Integer id)
{
    news News=newsService.Find(id);
    return  ResultVOUtil.success(News);
}

@ResponseBody
@DeleteMapping("/{id}")
    public  ResultVO delete(@RequestParam Integer id)
    {
        newsService.Delete(id);
        return  ResultVOUtil.success();
    }
}
