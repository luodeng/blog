package com.roden.blog.api.service;

import com.roden.blog.api.dao.ArticleDAO;
import com.roden.blog.api.domain.ArticleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleDAO articleDAO;

    public ArticleDO get(Integer id){
        return articleDAO.get(id);
    }
    public List<ArticleDO> listAll(){
        return  articleDAO.listAll();
    }
    public int count(){
        return articleDAO.count();
    }
    public int save(ArticleDO articleDO){
        return articleDAO.insert(articleDO);
    }
    public int remove(Integer id){
        return articleDAO.delete(id);
    }
    public int update(ArticleDO articleDO){
        return articleDAO.update(articleDO);
    }
}
