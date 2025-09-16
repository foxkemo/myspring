package org.unomi.myspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unomi.myspring.entity.Article;
import org.unomi.myspring.entity.ArticleLike;
import org.unomi.myspring.mapper.ArticleLikeMapper;
import org.unomi.myspring.mapper.ArticleMapper;
import org.unomi.myspring.mapper.UserMapper;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleLikeMapper articleLikeMapper;

    public List<Article> getList(int index , int size,String str){


           List<Article>  articleList= articleMapper.findByTitle(index,size ,str);
            articleList.forEach(article -> {
               article.setAuthor_name( userMapper.findUsernameById(article.getAuthor_id()));

            });
            return articleList;

    }


    public short addLike(int article_id,int uid){

        ArticleLike articleLike = new ArticleLike();
        articleLike.setArticle_id(article_id);
        articleLike.setUser_id(uid);
        if (  articleLikeMapper.isLiked(article_id,uid) !=0){

            return 2;
        }else{

          articleLikeMapper.insertLike(articleLike);
          articleMapper.updateLikeCountIncrease(article_id);
          return 1;
        }


    }

    public  short removeLike(int article_id,int uid){
        if (  articleLikeMapper.isLiked(article_id,uid) ==0){

            return 2;
        }else{

          articleLikeMapper.deleteLike(article_id,uid);
          articleMapper.updateLikeCountDecrease(article_id);
            return 1;
        }


    }


}
