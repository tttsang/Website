package com.jking.computersite.service.Impl;

import com.jking.computersite.entity.Article;
import com.jking.computersite.mapper.ArticleMapper;
import com.jking.computersite.mapper.CatalogueMapper;
import com.jking.computersite.mapper.IndexProfessorMapper;
import com.jking.computersite.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CatalogueMapper catalogueMapper;

    @Override
    public Map<String, Object> find(String first, String second,int page,int pageSize) {
        int count = catalogueMapper.count(first,second);
        System.out.println(count);
        List<Article> articleList = getArticleList(first,second,page,pageSize);
        Map<String ,Object> map = new LinkedHashMap<>();
        int totalPage = count/pageSize;
        if (count%pageSize != 0){
            totalPage++;
        }
        map.put("pageList",articleList);
        map.put("totalItem", count);
        map.put("totalPage", count/pageSize);
        return map;
    }

    @Override
    public Map<String, Object> init() {
        Map<String ,Object> map = new LinkedHashMap<>();
        for (int i=0;i<6;i++){
            List<Article> articleList = getArticleList("index",""+i,0,8);
            map.put("part"+i, articleList);
        }
        return map;
    }

    @Override
    public List<Article> getArticleList(String first, String second, int page, int pageSize) {
        List<Article> articleList = catalogueMapper.find(first, second,page*pageSize,pageSize);
        for (Article article:articleList){
            article.setAuditor(null);
            article.setAuthor(null);
            article.setContent(null);
            if (article.getIsshow() == 1){
                article.setTime(null);
            }else {
                article.setPictureUrl(null);
            }
            article.setIsshow(null);
        }

        return articleList;
    }

    @Override
    public Article getArticle(String id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        article.setIsshow(null);
        article.setPictureUrl(null);
        return article;
    }


}
