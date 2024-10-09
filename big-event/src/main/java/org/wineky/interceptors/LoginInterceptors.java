package org.wineky.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.wineky.pojo.Result;
import org.wineky.utils.JwtUtil;
import org.wineky.utils.ThreadLocalUtil;

import java.util.Map;

@Component
public class LoginInterceptors implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //request是浏览器的请求（前端发过来的，应该在Header中携带token）
        String token = request.getHeader("Authorization");
        try {
            Map<String,Object> claims = JwtUtil.parseToken(token);
            ThreadLocalUtil.set(claims);
            //claims解析成功 放行
            return true;
        } catch (Exception e) {
            //设置响应状态码，response对象
            response.setStatus(401);
            //不放行
            return false;
        }

    }
}
