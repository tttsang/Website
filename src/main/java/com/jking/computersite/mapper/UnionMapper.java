package com.jking.computersite.mapper;

import com.jking.computersite.entity.Union;

import java.util.List;

public interface UnionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Union record);

    int insertSelective(Union record);

    Union selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Union record);

    int updateByPrimaryKey(Union record);

    List<Union>Select();
}