package com.roden.blog.api.dao;

import com.roden.blog.api.domain.ArticleDO;

import java.util.List;

public interface ArticleDAO {
    ArticleDO get(Integer id);

    List<ArticleDO> listAll();

    int count();

    int insert(ArticleDO userDO);

    int delete(Integer id);

    int update(ArticleDO articleDO);
}
