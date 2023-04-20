package com.jcut.admin.controller;

import com.jcut.admin.param.AdminUserParam;
import com.jcut.admin.pojo.AdminUser;
import com.jcut.admin.service.AdminUserService;
import com.jcut.pojo.User;
import com.jcut.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/27 20:15
 **/
@RestController
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;
    @PostMapping("/user/login")
    public R login(@Validated AdminUserParam adminUserParam, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            return R.fail("核心参数为空,登录失败!");
        }
        //验证码校验
        String captcha = (String) session.getAttribute("captcha");
        if(!adminUserParam.getVerCode().equalsIgnoreCase(captcha)){
            return R.fail("验证码错误!");
        }
        AdminUser user = adminUserService.login(adminUserParam);
        if(user == null){
            return R.fail("登录失败!账号或者密码错误!");
        }
        session.setAttribute("userInfo",user);
        return R.ok("登录成功!");
    }
    @GetMapping("user/logout")
    public R logout(HttpSession session){
        session.invalidate();
        return R.ok("退出登录成功!");
    }
}
