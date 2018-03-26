package com.jking.computersite.mapper;

import com.jking.computersite.entity.catalogue;

import java.util.List;

public interface catalogueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(catalogue record);

    int insertSelective(catalogue record);

    catalogue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(catalogue record);

    int updateByPrimaryKey(catalogue record);

    List<catalogue>Show();

    List<catalogue>Index();

    List<catalogue> Column(Integer id);
}