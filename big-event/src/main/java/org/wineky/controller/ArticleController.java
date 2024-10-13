package org.wineky.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wineky.pojo.Article;
import org.wineky.pojo.Result;
import org.wineky.utils.JwtUtil;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result list(){
        //验证token,解析token获取用户数据，这一步在拦截器中完成
        return Result.success("所有的文章数据");
    }
}
