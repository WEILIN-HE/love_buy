package com.jcut.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jcut.admin.mapper.AdminUserMapper;
import com.jcut.admin.param.AdminUserParam;
import com.jcut.admin.pojo.AdminUser;
import com.jcut.admin.service.AdminUserService;
import com.jcut.constants.UserConstants;
import com.jcut.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/27 20:26
 **/
@Slf4j
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;
    @Override
    public AdminUser login(AdminUserParam adminUserParam) {
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", adminUserParam.getUserAccount());
        queryWrapper.eq("user_password", MD5Util.encode(adminUserParam.getUserPassword()+ UserConstants.USER_SLAT));
        AdminUser user = adminUserMapper.selectOne(queryWrapper);
        log.info("AdminUserServiceImpl.login业务结束,结果:{}",user);
        return user;
    }
}
