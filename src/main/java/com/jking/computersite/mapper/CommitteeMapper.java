package com.jking.computersite.mapper;

import com.jking.computersite.entity.Committee;

import java.util.List;

public interface CommitteeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Committee record);

    int insertSelective(Committee record);

    Committee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Committee record);

    int updateByPrimaryKey(Committee record);

    List<Committee>Select();
}