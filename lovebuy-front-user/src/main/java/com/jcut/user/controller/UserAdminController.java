package com.jcut.user.controller;

import com.jcut.param.CartListParam;
import com.jcut.param.PageParam;
import com.jcut.pojo.User;
import com.jcut.user.service.UserService;
import com.jcut.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/27 21:23
 **/
@RestController
@RequestMapping("user")
public class UserAdminController {
    @Autowired
    private UserService userService;
    @PostMapping("admin/list")
    public R listPage(@RequestBody PageParam pageParam){
        return userService.listPage(pageParam);
    }
    @PostMapping("admin/remove")
    public R remove(@RequestBody CartListParam cartListParam){
        return userService.remove(cartListParam.getUserId());
    }
    @PostMapping("admin/update")
    public R update(@RequestBody User user){
        return userService.update(user);
    }
    @PostMapping("admin/save")
    public R save(@RequestBody User user){
        return userService.save(user);
    }
}
