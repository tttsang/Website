package com.jking.computersite.service.Impl;

import com.jking.computersite.service.EsSearchService;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EsSearchServiceImpl implements EsSearchService {

    @Autowired
    private TransportClient client;

    @Override
    public String add(String title, String body) {
        try {
            XContentBuilder content = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("title", title)
                    .field("body", body)
                    .endObject();

            IndexResponse indexResponse = client.prepareIndex("computersite", "article").
                    setSource(content)
                    .get();
            return indexResponse.getId();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;

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
    public Map<String,Object> search(String keyword,int page,int pageSize,int wordSize) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.should(QueryBuilders.matchQuery("title", keyword));
        boolQuery.should(QueryBuilders.matchQuery("body", keyword));

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("computersite")
                .setTypes("article")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQuery)
                .setFrom(page - 1)
                .setSize(pageSize);

        SearchResponse response = searchRequestBuilder.get();

        List<Map<String,Object>> result = new ArrayList<>();
        SearchHits hits = response.getHits();
        for (SearchHit hit: hits){
            Map<String, Object> hitMap = hit.getSource();
            String body = (String) hitMap.get("body");
            if (body.length()>wordSize){
                body = body.substring(0,wordSize);
            }
            hitMap.put("body", body);
            hitMap.put("id", hit.getId());
            result.add(hitMap);
        }
        Map<String ,Object> map = new HashMap<>();
        long totalPage = hits.getTotalHits()/pageSize;
        if (hits.getTotalHits()%pageSize != 0){
            totalPage++;
        }
        map.put("totalPage", totalPage);
        map.put("totalItem", hits.getTotalHits());
        map.put("pageList", result);
        return map;
    }

    @Override
    public int getCount(String keyword) {
        return 0;
    }
}
