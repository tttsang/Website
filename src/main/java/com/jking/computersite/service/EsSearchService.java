package com.jking.computersite.service;

import java.util.List;
import java.util.Map;

public interface EsSearchService {

    String add(String title,String body);

    void update(String id,String title,String body);

    void delete(String id);

    void get(String id);

    Map<String,Object> search(String keyword,int page,int pageSize,int wordSize);

    int getCount(String keyword);
}
