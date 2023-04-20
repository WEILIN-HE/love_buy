package com.jcut.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jcut.constants.UserConstants;
import com.jcut.param.PageParam;
import com.jcut.param.UserCheckParam;
import com.jcut.param.UserLoginParam;
import com.jcut.pojo.User;
import com.jcut.user.mapper.UserMapper;
import com.jcut.user.service.UserService;
import com.jcut.utils.MD5Util;
import com.jcut.utils.R;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/11 22:06
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public R check(UserCheckParam userCheckParam) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userCheckParam.getUserName());
        Long total = userMapper.selectCount(queryWrapper);
        if (total == 0){
            log.info("UserServiceImpl.check业务结束，结果:{}","账号可以使用!");
            return R.ok("账号不存在，可以使用!");
        }
        log.info("UserServiceImpl.check业务结束,结果:{}","账号不可使用!");
        return R.fail("账号已经存在，不可注册!");
    }

    @Override
    public R register(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",user.getUserName());
        Long total = userMapper.selectCount(queryWrapper);
        if(total > 0){
            log.info("UserServiceImpl.register业务结束，结果:{}","账号存在，注册失败!");
            return R.fail("账号已经存在，不可注册!");
        }
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);

        int rows = userMapper.insert(user);
        if(rows == 0){
            log.info("UserServiceImpl.register业务结束,结果:{}","数据插入失败!");
            return R.fail("注册失败，请稍后再试!");
        }
        log.info("UserServiceImpl.register业务结束,结果:{}","注册成功!");
        return R.ok("注测成功!");
    }

    @Override
    public R login(UserLoginParam userLoginParam) {
        String newPwd = MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SLAT);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userLoginParam.getUserName());
        queryWrapper.eq("password",newPwd);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            log.info("UserServiceImpl.login业务结束,结果:{}","账号或者密码错误");
            return R.fail("账号或者密码错误!");
        }
        log.info("UserServiceImpl.login业务结束,结果:{}","登录成功!");
        user.setPassword(null);
        return R.ok("登录成功",user);
    }

    @Override
    public R listPage(PageParam pageParam) {
        IPage<User> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        page = userMapper.selectPage(page, null);
        List<User> records = page.getRecords();
        long total = page.getTotal();
        return R.ok("用户管理查询成功!",records,total);
    }

    @Override
    public R remove(Integer userId) {
        int i = userMapper.deleteById(userId);
        log.info("UserServiceImpl.remove业务结束,结果:{}",i);
        return R.ok("用户数据删除成功!");
    }

    @Override
    public R update(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId());
        queryWrapper.eq("password",user.getPassword());
        Long aLong = userMapper.selectCount(queryWrapper);
        if(aLong == 0) {
            user.setPassword(MD5Util.encode(user.getPassword()+UserConstants.USER_SLAT));
        }
        int i = userMapper.updateById(user);
        log.info("UserServiceImpl.update业务结束,结果:{}",i);
        return R.ok("用户信息修改成功!");
    }

    @Override
    public R save(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",user.getUserName());
        Long total = userMapper.selectCount(queryWrapper);
        if(total > 0){
            log.info("UserServiceImpl.register业务结束，结果:{}","账号存在，添加失败!");
            return R.fail("账号已经存在，不可添加!");
        }
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);

        int rows = userMapper.insert(user);
        if(rows == 0){
            log.info("UserServiceImpl.register业务结束,结果:{}","添加失败!");
            return R.fail("添加失败，请稍后再试!");
        }
        log.info("UserServiceImpl.register业务结束,结果:{}","添加成功!");
        return R.ok("添加成功!");
    }
}
