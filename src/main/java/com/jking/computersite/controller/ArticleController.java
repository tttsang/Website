package com.jking.computersite.controller;

import com.jking.computersite.VO.ArticleVO;
import com.jking.computersite.VO.ResultVO;
import com.jking.computersite.constant.UploadConstant;
import com.jking.computersite.entity.Article;
import com.jking.computersite.entity.Catalogue;
import com.jking.computersite.service.ArticleService;
import com.jking.computersite.service.CatalogueService;
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
    private CatalogueService catalogueService;

    @Autowired
    private EsSearchService esSearchService;

    @ResponseBody
    @PostMapping("/")
    public ResultVO add(ArticleVO articleVO){
        System.out.println(articleVO);
        Article article = new Article();
//        if (articleVO.getIsshow()!=null && articleVO.getIsshow() == 1) {
//            FileUtil.isImage(file);
//            String extension = FileUtil.getExtension(file);
//            String filePath = UploadConstant.INDEXPROFESSOR + UUID.randomUUID() + "." + extension;
//
//            //保存图片
//            FileUtil.saveFile(file, UploadConstant.PUBLIC + filePath);
//            //保存到数据库
//            article.setPictureUrl(filePath);
//        }

        //同步到ES中
//      String id = esSearchService.add(articleVO.getTitle(),articleVO.getContent());

        String id = new Date().getTime() + "";
        article.setId(id);
        article.setIsshow(articleVO.getIsshow());
        article.setTitle(articleVO.getTitle());
        article.setIsshow(articleVO.getIsshow());
        article.setContent(articleVO.getContent());
        article.setAuthor(articleVO.getAuthor());
        article.setAuditor(articleVO.getAuditor());
        articleService.add(article);
        List<String> publishTo = articleVO.getPublishTo();
        for(String s: publishTo){
            Catalogue catalogue = new Catalogue();
            catalogue.setId(id);
            catalogue.setFirstLevel(s.split("-")[0]);
            catalogue.setSecondLevel(s.split("-")[1]);
            catalogueService.add(catalogue);
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
