package com.jcut.user.controller;

import com.jcut.param.UserCheckParam;
import com.jcut.param.UserLoginParam;
import com.jcut.pojo.User;
import com.jcut.user.service.UserService;
import com.jcut.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/11 21:55
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userservice;
    @PostMapping("check")
    public R check(@RequestBody @Validated  UserCheckParam userCheckParam,BindingResult result){
        boolean b = result.hasErrors();

        if (b) {
            return  R.fail("账号为null,不可使用");
        }
        return userservice.check(userCheckParam);
    }
    @PostMapping("register")
    public R register(@RequestBody @Validated User user, BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数异常，不可注册!");

        }
        return userservice.register(user);
    }

    @PostMapping("login")
    public R login(@RequestBody @Validated UserLoginParam userLoginParam,BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数异常，不可登录!");

        }
        return userservice.login(userLoginParam);
    }
}
