package com.jcut.admin.service.impl;

import com.jcut.admin.service.UserService;
import com.jcut.clients.UserClient;
import com.jcut.param.CartListParam;
import com.jcut.param.PageParam;
import com.jcut.pojo.User;
import com.jcut.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/27 21:35
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserClient userClient;
    @Cacheable(value = "list.user",key = "#pageParam.currentPage+'-'+#pageParam.pageSize")
    @Override
    public R userList(PageParam pageParam) {
        log.info("UserServiceImpl.userList业务开始,参数:{}",pageParam);
        R r = userClient.adminListPage(pageParam);
        log.info("UserServiceImpl.userList业务结束,结果:{}",r);
        return r;
    }


    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R userRemove(CartListParam param) {
        R r = userClient.adminRemove(param);
        log.info("UserServiceImpl.userRemove业务结束,结果:{}",r);
        return r;
    }
    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R userUpdate(User user) {
        R r = userClient.adminUpdate(user);
        log.info("UserServiceImpl.userUpdate业务结束,结果:{}",r);
        return r;
    }
    @CacheEvict(value = "list.user",allEntries = true)
    @Override
    public R save(User user) {
        R r = userClient.adminSave(user);
        log.info("UserServiceImpl.save业务结束,结果:{}",r);
        return r;
    }
}
