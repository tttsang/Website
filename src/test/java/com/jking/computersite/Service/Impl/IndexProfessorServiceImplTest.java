package com.jking.computersite.Service.Impl;

import com.jking.computersite.Service.IndexProfessorService;
import com.jking.computersite.entity.IndexProfessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexProfessorServiceImplTest {

    @Autowired
    private IndexProfessorService indexProfessorService;

    @Test
    public void test(){
        IndexProfessor indexProfessor = new IndexProfessor();
        indexProfessor.setName("钟");
        indexProfessor.setPictureurl("...");
        indexProfessorService.add(indexProfessor);
    }

}