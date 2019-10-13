package com.roden.blog.api.controller;

import com.github.pagehelper.PageHelper;
import com.roden.blog.api.domain.ArticleDO;
import com.roden.blog.api.service.ArticleService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@Log
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/get")
    ArticleDO get() {
        log.info("get");
        return articleService.get(1);
    }

    @GetMapping("/listAll")
    List<ArticleDO> listAll() {
        PageHelper.startPage(1, 2);
        return articleService.listAll();
    }

    @GetMapping("/count")
    int count() {
        return articleService.count();
    }

    @RequestMapping("/save")
    int save(ArticleDO articleDO) {
        return articleService.save(articleDO);
    }

    @RequestMapping("/remove")
    int remove() {
        return articleService.remove(1);
    }

    @PostMapping("/update")
    int update() {
        return articleService.update(null);
    }
}