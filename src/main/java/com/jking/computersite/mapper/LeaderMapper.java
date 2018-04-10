package com.jking.computersite.mapper;

import com.jking.computersite.entity.Leader;

import java.util.List;

public interface LeaderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Leader record);

    int insertSelective(Leader record);

    Leader selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Leader record);

    int updateByPrimaryKey(Leader record);

    List<Leader>select();

    List<Leader>selectall();

    int number();

    List<Leader>select_by_number(int i);

}