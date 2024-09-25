package org.wineky.spring_mybatis.service;

import org.wineky.spring_mybatis.pojo.User;

public interface UserService {
    public User findById(Integer id);

}
