package org.wineky.spring_mybatis.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wineky.spring_mybatis.pojo.User;
import org.wineky.spring_mybatis.service.UserService;

@RestController
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("/findById")
    public User findById(Integer id){
        return userService.findById(id);
    }
}
