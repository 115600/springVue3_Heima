package org.winkey.spring_mybatis.service;

import org.winkey.spring_mybatis.pojo.User;

public interface UserService {
    public User findById(Integer id);

}
