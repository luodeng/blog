package com.roden.blog.api.config;

import com.roden.blog.api.service.JwtService;
import io.jsonwebtoken.Claims;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setCharacterEncoding("utf-8");
        //获取请求传来的token
        String token = request.getHeader("authorization");
        if(token == null){
            response.getWriter().write("请携带token");
            return;
        }
        Claims claims = JwtService.parseJWT(token); //验证token
        if (claims == null) {
            response.getWriter().write("请携带token");
        }else {
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
