package org.wineky.controller;

import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wineky.pojo.Result;
import org.wineky.pojo.User;
import org.wineky.service.UserService;
import org.wineky.utils.JwtUtil;
import org.wineky.utils.Md5Util;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}")String username, @Pattern(regexp = "^\\S{5,16}")String password){
        //查询用户需要用到Service
       User u = userService.findByUserName(username);
       if(u==null){
           //没有占用
           //注册
           userService.register(username,password);
           return Result.success();

       }else {
           //占用
           return Result.error("用户名已被占用");
       }
        //注册用户
    }
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}")String username, @Pattern(regexp = "^\\S{5,16}")String password){
        User loginUser = userService.findByUserName(username);
        //ctrl+alt+v快速获取对象
        if(loginUser==null){
            return Result.error("用户不存在");
        }
        //登录校验应放在Service层比较合适
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误！");

    }
    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token){

        Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);

    }
}
