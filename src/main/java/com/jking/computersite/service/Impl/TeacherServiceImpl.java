package com.jking.computersite.service.Impl;

import com.jking.computersite.entity.Teacher;
import com.jking.computersite.mapper.TeacherMapper;
import com.jking.computersite.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher select(Integer id)
    {
        Teacher teacher=teacherMapper.selectByPrimaryKey(id);
        return  teacher;
    }
}
