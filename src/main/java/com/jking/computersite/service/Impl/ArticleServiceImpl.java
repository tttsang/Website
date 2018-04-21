package com.jking.computersite.service.Impl;

import com.jking.computersite.entity.Article;
import com.jking.computersite.entity.Catalogue;
import com.jking.computersite.entity.XxxyContentWithBLOBs;
import com.jking.computersite.mapper.ArticleMapper;
import com.jking.computersite.mapper.CatalogueMapper;
import com.jking.computersite.mapper.XxxyContentMapper;
import com.jking.computersite.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CatalogueMapper catalogueMapper;

    @Autowired
    private XxxyContentMapper xxxyContentMapper;

    @Override
    public Map<String, Object> find(String first, String second,int page,int pageSize) {
        int count = catalogueMapper.count(first,second);
        System.out.println(count);
        List<Article> articleList = getArticleList(first,second,page,pageSize);
        Map<String ,Object> map = new LinkedHashMap<>();
        int totalPage = count/pageSize;
        if (count%pageSize != 0){
            totalPage++;
        }
        map.put("pageList",articleList);
        map.put("totalItem", count);
        map.put("totalPage", totalPage);
        return map;
    }

    @Override
    public Map<String, Object> init() {
        Map<String ,Object> map = new LinkedHashMap<>();
        for (int i=0;i<6;i++){
            List<Article> articleList = getArticleList("index",""+i,0,8);
            map.put(""+i, articleList);
        }
        return map;
    }

    @Override
    public List<Article> getArticleList(String first, String second, int page, int pageSize) {
        System.out.println(page*pageSize);
        List<Article> articleList = catalogueMapper.find(first, second,page*pageSize,pageSize);
        for (Article article:articleList){
            article.setAuditor(null);
            article.setAuthor(null);
            article.setContent(null);
            article.setIsshow(null);
        }
        return articleList;
    }

    @Override
    public Article getArticle(String id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        article.setIsshow(null);
        article.setPictureUrl(null);
        return article;
    }

    @Override
    public void delete(String id) {
        articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Article article) {
        articleMapper.insertSelective(article);
    }

    @Override
    public void test() {

        SimpleDateFormat sdf = new SimpleDateFormat(("yyyy-MM-dd hh:mm:ss"));
//        XxxyContentWithBLOBs xxxyContentWithBLOBs = xxxyContentMapper.selectByPrimaryKey(63);
//        System.out.println(xxxyContentWithBLOBs.getIntrotext().length());
//        Article article = new Article();
//        article.setId(xxxyContentWithBLOBs.getId()+"");
//
//        article.setContent(xxxyContentWithBLOBs.getIntrotext());
//        articleMapper.insertSelective(article);

        List<XxxyContentWithBLOBs> xxxyContentWithBLOBs = xxxyContentMapper.selectAll();
        for (XxxyContentWithBLOBs item: xxxyContentWithBLOBs){


//            Article article = new Article();
//            article.setId(item.getId()+"");
//            if (item.getCatid() == 57){
//                article.setIsshow(1);
//            }
            try {
//                article.setTime(sdf.parse(item.getCreated()));
//                article.setTitle(item.getTitle());
//                article.setAuthor("曾令梅");
//                article.setAuditor("曾令梅");
//                article.setContent(item.getIntrotext());
//                article.setPictureUrl(item.getImages());
//                articleMapper.insert(article);
                Catalogue catalogue = new Catalogue();
//                if (item.getCatid() == 57){
//                    catalogue.setId(item.getId()+"");
//                    catalogue.setFirstLevel("index");
//                    catalogue.setSecondLevel("0");
//                }else if (item.getCatid() == 58){
//                    catalogue.setId(item.getId()+"");
//                    catalogue.setFirstLevel("index");
//                    catalogue.setSecondLevel("2");
//                }else if (item.getCatid() == 35){
//                    catalogue.setId(item.getId()+"");
//                    catalogue.setFirstLevel("situation");
//                    catalogue.setSecondLevel("0");
//                }else if (item.getCatid() == 36){
//                    catalogue.setId(item.getId()+"");
//                    catalogue.setFirstLevel("situation");
//                    catalogue.setSecondLevel("3");
//                }else if (item.getCatid() == 45){
//                    catalogue.setId(item.getId()+"");
//                    catalogue.setFirstLevel("cultivation");
//                    catalogue.setSecondLevel("1");
//                }else if (item.getCatid() == 53){
//                    catalogue.setId(item.getId()+"");
//                    catalogue.setFirstLevel("research");
//                    catalogue.setSecondLevel("0");
//                }else if (item.getCatid() == 54){
//                    catalogue.setId(item.getId()+"");
//                    catalogue.setFirstLevel("research");
//                    catalogue.setSecondLevel("1");
//                }else if (item.getCatid() == 55){
//                    catalogue.setId(item.getId()+"");
//                    catalogue.setFirstLevel("research");
//                    catalogue.setSecondLevel("2");
//                }else if (item.getCatid() == 56){
//                    catalogue.setId(item.getId()+"");
//                    catalogue.setFirstLevel("research");
//                    catalogue.setSecondLevel("3");
//                }else if (item.getCatid() == 52){
//                    catalogue.setId(item.getId()+"");
//                    catalogue.setFirstLevel("power");
//                    catalogue.setSecondLevel("0");
//                }

                if (item.getCatid() == 93){
                    catalogue.setId(item.getId()+"");
                    catalogue.setFirstLevel("list");
                    catalogue.setSecondLevel("1");
                }else if (item.getCatid() == 97){
                    catalogue.setId(item.getId()+"");
                    catalogue.setFirstLevel("list");
                    catalogue.setSecondLevel("2");
                }else if (item.getCatid() == 83){
                    catalogue.setId(item.getId()+"");
                    catalogue.setFirstLevel("list");
                    catalogue.setSecondLevel("3");
                }else if (item.getCatid() == 60){
                    catalogue.setId(item.getId()+"");
                    catalogue.setFirstLevel("list");
                    catalogue.setSecondLevel("4");
                }else if (item.getCatid() == 61){
                    catalogue.setId(item.getId()+"");
                    catalogue.setFirstLevel("list");
                    catalogue.setSecondLevel("5");
                }
                catalogueMapper.insert(catalogue);
            }catch (Exception e){
                System.out.println(item.getId());
            }
//            article.setTime();
        }
    }
}
