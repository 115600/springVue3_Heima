package org.winkey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.winkey.pojo.Article;
import org.winkey.pojo.PageBean;
import org.winkey.pojo.Result;
import org.winkey.service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
     private ArticleService articleService;
    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
      PageBean<Article> pb =articleService.list(pageNum,pageSize,categoryId,state);
      return Result.success(pb);
    }
}
