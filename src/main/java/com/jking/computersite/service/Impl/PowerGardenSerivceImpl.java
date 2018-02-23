package com.jking.computersite.service.Impl;

import com.jking.computersite.entity.PowerGarden;
import com.jking.computersite.enums.CommonEnums;
import com.jking.computersite.exception.MyException;
import com.jking.computersite.mapper.PowerGardenMapper;
import com.jking.computersite.service.PowerGardenSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PowerGardenSerivceImpl implements PowerGardenSerivce {

    @Autowired
    private PowerGardenMapper powerGardenMapper;

    @Override
    public Map<String, List<PowerGarden>> getAll() {

        Map<String, List<PowerGarden>> map = new LinkedHashMap<>();
        int majorNumber;
        try {
            majorNumber = powerGardenMapper.getMajorNumber();
        }catch (Exception e){
            return null;
        }
        for (int i=1;i<=majorNumber;i++){
            List<PowerGarden> list = powerGardenMapper.selectByMajor(i);
            System.out.println(list);
            if (list != null){
                map.put(i+"",list);
            }
        }
        return map;
    }

    @Override
    public void add(PowerGarden powerGarden) {
        if (powerGardenMapper.selectByMajorAndName(powerGarden.getMajor(),powerGarden.getName()) != null){
            throw new MyException(CommonEnums.DATA_REPEAT);
        }
        powerGardenMapper.insert(powerGarden);
    }

    @Override
    public void delele(int id) {
        int row = powerGardenMapper.deleteByPrimaryKey(id);
        if (row == 0){
            throw new MyException(CommonEnums.ID_NOT_FOUND);
        }
    }
}
