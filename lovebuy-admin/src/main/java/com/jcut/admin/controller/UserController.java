package com.jcut.admin.controller;

import com.jcut.admin.service.UserService;
import com.jcut.param.CartListParam;
import com.jcut.param.PageParam;
import com.jcut.pojo.User;
import com.jcut.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/27 21:33
 **/
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("list")
    public R userList(PageParam pageParam) {
        return userService.userList(pageParam);
    }

    @PostMapping("remove")
    public R userList(CartListParam pageParam) {
        return userService.userRemove(pageParam);
    }

    @PostMapping("update")
    public R update(User user) {
        return userService.userUpdate(user);
    }

    @PostMapping("save")
    public R save(User user) {
        return userService.save(user);
    }
}
