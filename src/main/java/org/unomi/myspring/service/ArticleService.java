package org.unomi.myspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unomi.myspring.entity.Article;
import org.unomi.myspring.mapper.ArticleMapper;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    public List<Article> getList(int index , int size){


            return articleMapper.findIntoList(index,size);



    }


}
