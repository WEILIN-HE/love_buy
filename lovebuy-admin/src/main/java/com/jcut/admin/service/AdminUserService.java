package com.jcut.admin.service;

import com.jcut.admin.param.AdminUserParam;
import com.jcut.admin.pojo.AdminUser;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/27 20:25
 **/
public interface AdminUserService {
    AdminUser login(AdminUserParam adminUserParam);
}
