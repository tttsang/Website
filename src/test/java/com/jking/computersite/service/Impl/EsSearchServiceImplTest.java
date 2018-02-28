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
        searchService.add("中国","中华人们共和国");
    }

    @Test
    public void TestUpdate(){
        searchService.update("AWHcgGFhy8X-cDffTLCX","中国","中华人们共和国2");
    }

    @Test
    public void TestDel(){
        searchService.delete("AWHcgGFhy8X-cDffTLCX");
    }

    @Test
    public void TestGet(){
        searchService.get("AWHcfZdQy8X-cDffTLCW");
    }
}