package com.jcut.user.service;

import com.jcut.param.PageParam;
import com.jcut.param.UserCheckParam;
import com.jcut.param.UserLoginParam;
import com.jcut.pojo.User;
import com.jcut.utils.R;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/11 22:04
 **/
public interface UserService {
    R check(UserCheckParam userCheckParam);

    R register(User user);

    R login(UserLoginParam userLoginParam);

    R listPage(PageParam pageParam);

    R remove(Integer userId);

    R update(User user);

    R save(User user);
}
