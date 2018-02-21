package com.jking.computersite.mapper;

import com.jking.computersite.entity.PowerGarden;

public interface PowerGardenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PowerGarden record);

    int insertSelective(PowerGarden record);

    PowerGarden selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PowerGarden record);

    int updateByPrimaryKey(PowerGarden record);
}