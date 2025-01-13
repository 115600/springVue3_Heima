package org.winkey.spring_mybatis.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winkey.spring_mybatis.pojo.User;
import org.winkey.spring_mybatis.service.UserService;

@RestController
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("/findById")
    public User findById(Integer id){
        return userService.findById(id);
    }
}
