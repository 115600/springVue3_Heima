package org.wineky.spring_mybatis.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wineky.spring_mybatis.mapper.UserMapper;
import org.wineky.spring_mybatis.pojo.User;
import org.wineky.spring_mybatis.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
