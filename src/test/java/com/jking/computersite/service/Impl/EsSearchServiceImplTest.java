package com.jking.computersite.service.Impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsSearchServiceImplTest {

    @Autowired
    EsSearchServiceImpl searchService;

    @Test
    public void TestAdd(){
        searchService.add("12","12");
    }
}