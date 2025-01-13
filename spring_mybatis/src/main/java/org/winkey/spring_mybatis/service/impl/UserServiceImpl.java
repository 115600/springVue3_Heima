package org.winkey.spring_mybatis.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.winkey.spring_mybatis.mapper.UserMapper;
import org.winkey.spring_mybatis.pojo.User;
import org.winkey.spring_mybatis.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
