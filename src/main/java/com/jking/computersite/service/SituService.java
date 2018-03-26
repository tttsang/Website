package com.jking.computersite.service;

import com.github.pagehelper.PageInfo;
import com.jking.computersite.entity.Situ;

public interface SituService {
    PageInfo<Situ> selectAll(int pageNum, int pageSize);

    void add(Situ situ);

    Situ find(Integer id);

    int delete(Integer id);

    int update(Situ situ);
}
