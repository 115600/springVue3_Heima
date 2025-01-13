package org.winkey.service;

import org.winkey.pojo.Article;
import org.winkey.pojo.PageBean;

public interface ArticleService {
    //新增文章
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
