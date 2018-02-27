package com.jking.computersite.service.Impl;

import com.jking.computersite.service.EsSearchService;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EsSearchServiceImpl implements EsSearchService {

    @Autowired
    private TransportClient client;

    @Override
    public void add(String title, String body) {
        try {
            //构建json格式的数据
            XContentBuilder content = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("title", title)
                    .field("body", body)
                    .endObject();
            System.out.println(content);
            //发送post请求
            client.prepareIndex("computersite", "article").
                    setSource(content)
                    .get();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(String id, String title, String body) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void get(String id) {

    }
}
