package org.wineky.controller;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wineky.pojo.Result;
import org.wineky.pojo.User;
import org.wineky.service.UserService;
import org.wineky.utils.JwtUtil;
import org.wineky.utils.Md5Util;
import org.wineky.utils.ThreadLocalUtil;

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
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){

        // Map<String, Object> map = JwtUtil.parseToken(token);
        Map<String,Object> map =  ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {

        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    // 增加URL验证
    public Result updateAvatar(@RequestParam @URL String avatarUrl){

        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    @PatchMapping("/updatePwd")
    // 因为键名与user实体类不一致，所以用map接受，MVC框架自动将json转换成map集合对象
    public Result updatePwd(@RequestBody Map<String,String> params){
        // 1.校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
         if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
             return Result.error("缺少必要的参数");
         }

         // 原密码是否正确
         // 调用userService根据用户名拿到原密码
         Map<String,Object> map = ThreadLocalUtil.get();
        String username  = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写不正确");
        }

        // newPwd和rePwd是否一致
        if(!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一致");
        }

        userService.updatePwd(newPwd);
        return Result.success();

    }
}
