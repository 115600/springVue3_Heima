package org.winkey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.winkey.mapper.CategoryMapper;
import org.winkey.pojo.Category;
import org.winkey.service.CategoryService;
import org.winkey.utils.ThreadLocalUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

// 把对象交给容器
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        // 还要知道使谁添加的分类
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        return categoryMapper.list(userId);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }

}
