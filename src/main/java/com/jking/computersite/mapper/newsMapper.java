package com.jking.computersite.mapper;

import com.jking.computersite.entity.news;

import java.util.List;

public interface newsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(news record);

    int insertSelective(news record);

    news selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(news record);

    int updateByPrimaryKey(news record);

    List<news>Article(int id);
}