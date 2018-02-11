package com.jking.computersite.service.Impl;

import com.jking.computersite.service.IndexProfessorService;
import com.jking.computersite.entity.IndexProfessor;
import com.jking.computersite.enums.IndexProfessorEums;
import com.jking.computersite.exception.MyException;
import com.jking.computersite.mapper.IndexProfessorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexProfessorServiceImpl implements IndexProfessorService {

    @Autowired
    private IndexProfessorMapper indexProfessorMapper;

    @Override
    public List<IndexProfessor> getAll() {
        return indexProfessorMapper.selectAll();
    }

    @Override
    public IndexProfessor find(Integer id) {
        IndexProfessor indexProfessor = indexProfessorMapper.selectByPrimaryKey(id);
        if (indexProfessor == null){
            throw new MyException(IndexProfessorEums.ID_NOT_FOUND);
        }
        return indexProfessor;
    }

    @Override
    public void add(IndexProfessor indexProfessor) {
        indexProfessorMapper.insert(indexProfessor);
    }

    @Override
    public int delete(Integer id) {
        int record = indexProfessorMapper.deleteByPrimaryKey(id);
        if (record == 0){
            throw new MyException(IndexProfessorEums.ID_NOT_FOUND);
        }
        return record;
    }

    @Override
    public int update(IndexProfessor indexProfessor) {
        System.out.println(indexProfessor);
        int record = indexProfessorMapper.updateByPrimaryKeySelective(indexProfessor);
        if (record == 0){
            throw new MyException(IndexProfessorEums.ID_NOT_FOUND);
        }
        return record;
    }
}
