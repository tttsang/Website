package com.jking.computersite.service;

import com.jking.computersite.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    Map<String,Object> find(String first,String second,int page,int pageSize);

    Map<String,Object> init();

    List<Article> getArticleList(String first, String second, int page, int pageSize);

    Article getArticle(String id);

    void delete(String id);

    void add(Article article);

    void test();
}
