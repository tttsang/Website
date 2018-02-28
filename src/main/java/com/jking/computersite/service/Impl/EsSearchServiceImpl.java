package com.jking.computersite.service.Impl;

import com.jking.computersite.service.EsSearchService;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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
            XContentBuilder content = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("title", title)
                    .field("body", body)
                    .endObject();

            client.prepareIndex("computersite", "article").
                    setSource(content)
                    .get();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(String id, String title, String body) {
        try {
            UpdateRequest updateRequest = new UpdateRequest("computersite", "article", id);
            XContentBuilder content = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("title", title)
                    .field("body", body)
                    .endObject();
            updateRequest.doc(content);

            client.update(updateRequest).get();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(String id) {
        client.prepareDelete("computersite", "article", id).get();
    }

    @Override
    public void get(String id) {
        client.prepareGet("computersite", "article", id).get();
    }

    @Override
    public void search(String keyword) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.should(QueryBuilders.matchQuery("title", keyword));
        queryBuilder.should(QueryBuilders.matchQuery("body", keyword));
    }
}
