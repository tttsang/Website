package com.jking.computersite.service;

import com.jking.computersite.entity.PowerGarden;

import java.util.List;
import java.util.Map;

public interface PowerGardenSerivce {

    Map<String,List<PowerGarden>> getAll();

    void add(PowerGarden powerGarden);

    void delele(int id);

}
