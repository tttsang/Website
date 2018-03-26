package com.jking.computersite.mapper;

import com.jking.computersite.entity.Situ;

import java.util.List;

public interface SituMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Situ record);

    int insertSelective(Situ record);

    Situ selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Situ record);

    int updateByPrimaryKey(Situ record);

    List<Situ>selectAll();
}