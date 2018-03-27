package com.jking.computersite.controller;

import com.jking.computersite.VO.ArticleVO;
import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.constant.UploadConstant;
import com.jking.computersite.entity.Article;
import com.jking.computersite.entity.Catalogue;
import com.jking.computersite.service.ArticleService;
import com.jking.computersite.service.EsSearchService;
import com.jking.computersite.utils.FileUtil;
import com.jking.computersite.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("article")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private EsSearchService esSearchService;

    @ResponseBody
    @PostMapping("/")
    public ResultVO add(ArticleVO articleVO,@RequestParam(required = false)  MultipartFile file){
        System.out.println(articleVO);
        if (articleVO.getIsshow() == 1){
            FileUtil.isImage(file);
            String extension = FileUtil.getExtension(file);
            String filePath = UploadConstant.INDEXPROFESSOR + UUID.randomUUID() + "." +extension;

            //同步到ES中
//            String id = esSearchService.add(articleVO.getTitle(),articleVO.getContent());
            String id = new Date().getTime() + "";

            //保存图片
            FileUtil.saveFile(file,UploadConstant.PUBLIC + filePath);
            //保存到数据库
            Article article = new Article();
            article.setId(id);
            article.setTitle(articleVO.getTitle());
            article.setPictureUrl(filePath);
            article.setIsshow(articleVO.getIsshow());
            article.setContent(article.getContent());
            article.setAuthor(article.getAuthor());
            article.setAuditor(article.getAuditor());
            List<Map<String,String>> publishTo = articleVO.getPublishTo();
            for(Map<String, String> map: publishTo){

                Catalogue catalogue = new Catalogue();
                catalogue.setId(id);
                catalogue.setFirstLevel(map.get("first_level"));
                catalogue.setSecondLevel(map.get("second_level"));

            }

        }
        return ResultVOUtil.success();
    }

    @ResponseBody
    @GetMapping("/{first}/{second}")
    public ResultVO getList(@PathVariable String first,
                        @PathVariable String second,
                        @RequestParam(required = false, defaultValue = "1") int currentPage,
                        @RequestParam(required = false, defaultValue = "15") int pageSize
                        ){
        return ResultVOUtil.success(articleService.find(first,second,currentPage-1,pageSize));
    }

    @ResponseBody
    @GetMapping("/indexInit")
    public ResultVO get(){
        Map<String, Object> map = new LinkedHashMap<>();

        return ResultVOUtil.success(articleService.init());
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResultVO getOne(@PathVariable String id){
        Map<String, Object> map = new LinkedHashMap<>();

        return ResultVOUtil.success(articleService.getArticle(id));
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable String id){
        articleService.delete(id);
        return ResultVOUtil.success();
    }

}
