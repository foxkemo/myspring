package org.unomi.myspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unomi.myspring.entity.Article;
import org.unomi.myspring.mapper.ArticleMapper;
import org.unomi.myspring.service.ArticleService;

import java.util.List;

@RestController()
@RequestMapping("/article")
public class ArticleController {
    @Autowired
 ArticleMapper articleMapper;
@Autowired
ArticleService articleService;
    public ArticleController(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @RequestMapping("/getArticle")
    public Article getArticle(@RequestParam int id, HttpSession session) throws JsonProcessingException {
       Article article= articleMapper.findById(id) ;
        if ( article !=null) {

            session.setAttribute("article", id);
            return article;

        } else {
            return null;
        }

    }


    @RequestMapping("/renderArticle")
    public Article renderArticle(HttpSession session) {
            Integer id= (Integer) session.getAttribute("article");
            Article article= articleMapper.findById(id) ;
            return article;

    }


    @RequestMapping("/getArticleList")
    public List<Article> getArticleList (@RequestParam int page, @RequestParam int size) {
        int index=(page-1)*size;
        return articleService.getList(index,size);



    }



}


