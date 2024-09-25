package org.wineky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wineky.mapper.UserMapper;
import org.wineky.pojo.User;
import org.wineky.service.UserService;
import org.wineky.utils.Md5Util;

//将当前对象注入容器
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        //调用mapper
        return u;
    }

    @Override
    public void register(String username, String password) {
        //加密密码再注册
         String md5String =  Md5Util.getMD5String(password);
         userMapper.add(username,md5String);
        //添加
    }
}
