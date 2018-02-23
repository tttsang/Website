package com.jking.computersite.mapper;

import com.jking.computersite.entity.PowerGarden;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PowerGardenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PowerGarden record);

    int insertSelective(PowerGarden record);

    PowerGarden selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PowerGarden record);

    int updateByPrimaryKey(PowerGarden record);

    int getMajorNumber();

    List<PowerGarden> selectByMajor(int major);

    PowerGarden selectByMajorAndName(@Param("major") int major,@Param("name") String name);
}