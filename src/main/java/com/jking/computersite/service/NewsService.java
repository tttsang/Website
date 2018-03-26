package com.jking.computersite.service;

import com.github.pagehelper.PageInfo;
import com.jking.computersite.entity.catalogue;
import com.jking.computersite.entity.news;

import java.util.List;

public interface NewsService {
    List<catalogue>Show();

    int Delete(Integer id);

    int Update(news news);

    news Find(Integer id);

    void post(catalogue catalogue,news news);

    List<catalogue>Index();

      PageInfo Column(int id,int pageNum, int pageSizei);

      List<news>Article(int id);
}
