package org.winkey.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.winkey.pojo.Article;
import org.winkey.pojo.PageBean;
import org.winkey.service.ArticleService;
import org.winkey.utils.ThreadLocalUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private org.winkey.mapper.articleMapper articleMapper;
    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        System.out.println(userId);
        article.setCreateUser(userId);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建Pagebean对象
        PageBean<Article> pb= new PageBean<>();

        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //分页查询会自动拼接sql语句


        //调用Mapper，只能查询本用户创建的文章
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.list(userId,categoryId,state);
        Page<Article> p = (Page<Article>)as;
        // PageInfo<Article> p= (PageInfo<Article>) as; 用PageInfo会报错
        /*
            将 Page 类型的对象转换为 PageInfo 类型，
            但这两者是 PageHelper 提供的不同类
            ，不能直接互相转换。以下是解决问题的具体方法：
        * */
        // pb.setItems(p.getList());
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

}
