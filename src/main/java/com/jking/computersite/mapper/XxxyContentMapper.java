package com.jking.computersite.mapper;

import com.jking.computersite.entity.XxxyContent;
import com.jking.computersite.entity.XxxyContentWithBLOBs;

import java.util.List;

public interface XxxyContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(XxxyContentWithBLOBs record);

    int insertSelective(XxxyContentWithBLOBs record);

    XxxyContentWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XxxyContentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(XxxyContentWithBLOBs record);

    int updateByPrimaryKey(XxxyContent record);

    List<XxxyContentWithBLOBs> selectAll();
}