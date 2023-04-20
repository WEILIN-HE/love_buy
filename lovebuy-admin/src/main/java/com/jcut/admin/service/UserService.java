package com.jcut.admin.service;

import com.jcut.param.CartListParam;
import com.jcut.param.PageParam;
import com.jcut.pojo.User;
import com.jcut.utils.R;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/27 21:34
 **/
public interface UserService {
    R userList(PageParam pageParam);

    //删除用户数据
    R userRemove(CartListParam cartListParam);

    //修改用户信息
    R userUpdate(User user);

    R save(User user);
}
