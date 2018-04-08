package com.jking.computersite.service.Impl;

import com.jking.computersite.service.ArticleService;
import com.jking.computersite.service.IndexProfessorService;
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

    @Autowired
    private ArticleService articleService;

    @Test
    public void test(){
        articleService.test();
    }

}