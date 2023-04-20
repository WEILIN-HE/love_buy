package com.jcut.admin.inteceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/27 21:08
 * 登陆保护拦截器
 **/
@Component
public class LoginProtectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{

        Object userInfo = request.getSession().getAttribute("userInfo");
        if(userInfo != null){
            return true;
        }else {
            response.sendRedirect(request.getContextPath()+"/index.html");
            return false;
        }
    }
}
