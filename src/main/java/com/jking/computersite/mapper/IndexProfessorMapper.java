package com.jking.computersite.mapper;

import com.jking.computersite.entity.IndexProfessor;

import java.util.List;

public interface IndexProfessorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IndexProfessor record);

    int insertSelective(IndexProfessor record);

    IndexProfessor selectByPrimaryKey(Integer id);

    List<IndexProfessor> selectAll();

    int updateByPrimaryKeySelective(IndexProfessor record);

    int updateByPrimaryKey(IndexProfessor record);
}