package com.jking.computersite.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jking.computersite.entity.Situ;
import com.jking.computersite.enums.SituEnums;
import com.jking.computersite.exception.MyException;
import com.jking.computersite.mapper.SituMapper;
import com.jking.computersite.service.SituService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SituServiceImpl implements SituService {
    @Autowired
    private SituMapper SituMapper;

    @Override
    public  PageInfo<Situ> selectAll(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
       List<Situ> situ=SituMapper.selectAll();
       PageInfo<Situ> page=new PageInfo<>(situ);
       return  page;
    }

    @Override
    public  void  add(Situ situ){ SituMapper.insert(situ);}

    @Override
    public  Situ find(Integer id)
    {
       Situ situ =SituMapper.selectByPrimaryKey(id);
       if (situ==null)
       {
           throw new MyException(SituEnums.ID_NOT_FOUND);
       }
       return  situ;
    }
    @Override
    public  int delete(Integer id)
    {
        int record=SituMapper.deleteByPrimaryKey(id);
        if(record==0)
        {
            throw new MyException(SituEnums.ID_NOT_FOUND);
        }
        return record;
    }
    @Override
    public  int update(Situ situ)
    {
        int record=SituMapper.updateByPrimaryKeySelective(situ);
        if (record==0) {
            throw new MyException(SituEnums.ID_NOT_FOUND);
        }
        return  record;
    }
}
