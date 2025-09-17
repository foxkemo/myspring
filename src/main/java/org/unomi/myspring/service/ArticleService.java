package org.unomi.myspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unomi.myspring.entity.Article;
import org.unomi.myspring.entity.ArticleLike;
import org.unomi.myspring.mapper.ArticleLikeMapper;
import org.unomi.myspring.mapper.ArticleMapper;
import org.unomi.myspring.mapper.CommentMapper;
import org.unomi.myspring.mapper.UserMapper;
import org.unomi.myspring.tool.Tool;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleLikeMapper articleLikeMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private Tool tool;

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


    public String getCommentList(int id, int page,int size){

                int index=(page-1)*size;
               ObjectMapper om = tool.getObjectMapper();


        try {
            return  om.writeValueAsString(  commentMapper.findByArticleId(id,index,size));
        } catch (JsonProcessingException e) {

           return  e.getMessage();
        }


    }


}
