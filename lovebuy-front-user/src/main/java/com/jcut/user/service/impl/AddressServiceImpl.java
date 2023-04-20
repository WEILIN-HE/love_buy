package com.jcut.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jcut.pojo.Address;
import com.jcut.user.mapper.AddressMapper;
import com.jcut.user.service.AddressService;
import com.jcut.utils.R;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/12 19:04
 **/
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public R list(Integer userId) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        List<Address> addressList = addressMapper.selectList(queryWrapper);
        R ok = R.ok("查询成功", addressList);
        log.info("AddressServiceImpl.list业务结束，结果:{}",ok);
        return ok;
    }

    @Override
    public R save(Address address) {
        int rows = addressMapper.insert(address);
        if(rows == 0){
            log.info("AddressServiceImpl.save业务结束，结果:{}","地址失败!");
            return R.fail("插入地址失败!");
        }
        return list(address.getUserId());
    }

    @Override
    public R remove(Integer id) {
        int rows = addressMapper.deleteById(id);
        if(rows == 0){
            log.info("AddressServiceImpl.remove业务结束,结果:{}","地址删除失败");
            return R.fail("删除地址数据失败!");
        }
        log.info("AddressServiceImpl.remove业务结束,结果:{}","地址删除失败");
        return R.ok("地址删除成功!");
    }
}
