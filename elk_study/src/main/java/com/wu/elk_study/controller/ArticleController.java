package com.wu.elk_study.controller;

import com.wu.elk_study.domain.Article;
import com.wu.elk_study.repository.ArticleRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    /**
     *新增或者是修改
     * @return
     */
    @GetMapping("save")
    public  Object save(){
        Article  article = new Article();

        article.setId(11);
        article.setPv(888);
        article.setComtent("this is content");
        article.setTitle("this you my you ohhh");
        article.setSummary("this is summary");
        articleRepository.save(article);
        return article;
    }


    /**
     * query 模糊查询
     * @param title
     * @return
     */
    @GetMapping("get")
    public  Object get(String title){
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", title);

        Iterable<Article> list =  articleRepository.search(queryBuilder);
        List list1 = new ArrayList();
       Iterator iterator =  list.iterator();
        while (iterator.hasNext()){
            list1.add(iterator.next());
        }
        return  list1;
    }
}
