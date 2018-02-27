package com.jking.computersite.service;

public interface EsSearchService {

    void add(String title,String body);

    void update(String id,String title,String body);

    void delete(String id);

    void get(String id);
}
