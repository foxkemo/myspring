package org.unomi.myspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unomi.myspring.entity.Article;
import org.unomi.myspring.mapper.ArticleMapper;
import org.unomi.myspring.mapper.UserMapper;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;

    public List<Article> getList(int index , int size,String str){


           List<Article>  articleList= articleMapper.findByTitle(index,size ,str);
            articleList.forEach(article -> {
               article.setAuthor_name( userMapper.findUsernameById(article.getAuthor_id()));

            });
            return articleList;

    }


}
