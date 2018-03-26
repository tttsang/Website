package com.jking.computersite.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jking.computersite.entity.Situ;
import com.jking.computersite.entity.catalogue;
import com.jking.computersite.entity.news;
import com.jking.computersite.enums.CommonEnums;
import com.jking.computersite.exception.MyException;
import com.jking.computersite.mapper.catalogueMapper;
import com.jking.computersite.mapper.newsMapper;
import com.jking.computersite.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    private catalogueMapper catalogueMapper;
    @Autowired
    private newsMapper newsMapper;

    @Override
    public List<catalogue>Show()
    {
    return catalogueMapper.Show();
    }

    @Override
    public  void post(catalogue catalogue,news news)
    {
        newsMapper.insert(news);
        catalogueMapper.insert(catalogue);
    }

    @Override
    public int Delete(Integer id)
    {
        int record=catalogueMapper.deleteByPrimaryKey(id);
        int rrecord=newsMapper.deleteByPrimaryKey(id);
        if (record==0&&rrecord==0)
        {
            throw new MyException(CommonEnums.ID_NOT_FOUND);
        }
        return record;
    }

    @Override
    public news Find(Integer id)
    {
        return newsMapper.selectByPrimaryKey(id);

    }

    @Override
    public int Update(news news)
    {

        int rrecord=newsMapper.updateByPrimaryKeySelective(news);
        if (rrecord==0)
        {
            throw new MyException(CommonEnums.ID_NOT_FOUND);
        }
        return rrecord;
    }

    @Override
    public  List<catalogue>Index()
    {
        return catalogueMapper.Index();
    }

    @Override
    public  PageInfo Column(int id,int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<catalogue> catalogues =catalogueMapper.Column(id);
        PageInfo<catalogue> page;
        page = new PageInfo<>(catalogues);
        return  page;
    }

    @Override
    public  List<news>Article(int id)
    {
        return newsMapper.Article(id);
    }
}
