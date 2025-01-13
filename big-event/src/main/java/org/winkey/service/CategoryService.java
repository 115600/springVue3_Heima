package org.winkey.service;

import org.winkey.pojo.Category;

import java.util.List;

public interface CategoryService {
    // 新增分类
    void add(Category category);
    // 列表查询
    List<Category> list();
    // 根据文章id查询
    Category findById(Integer id);

    // 更新文章分类
    void update(Category category);
    //删除文章分类
    void deleteById(Integer id);
}
