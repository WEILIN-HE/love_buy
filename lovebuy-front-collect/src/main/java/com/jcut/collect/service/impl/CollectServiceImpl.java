package com.jcut.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jcut.clients.ProductClient;
import com.jcut.collect.mapper.CollectMapper;
import com.jcut.collect.service.CollectService;
import com.jcut.param.ProductCollectParam;
import com.jcut.param.ProductIdsParam;
import com.jcut.pojo.Collect;
import com.jcut.pojo.Product;
import com.jcut.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/20 22:04
 **/
@Service
@Slf4j
public class CollectServiceImpl implements CollectService {

    /**
     * 收藏保存服务
     * @param collectParam
     * @return
     */
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private ProductClient productClient;
    @Override
    public R save(Collect collect) {
        //数据库查询
        QueryWrapper<Collect> queryWrapper
                = new QueryWrapper<>();
        queryWrapper.eq("user_id",collect.getUserId());
        queryWrapper.eq("product_id",collect.getProductId());
        Long count = collectMapper.selectCount(queryWrapper);

        if ( count> 0){
            log.info("CollectServiceImpl.save业务结束，结果:{}",count);
            return R.fail("商品已在收藏夹! 无需二次添加!");
        }
        collect.setCollectTime(System.currentTimeMillis());
        //数据库插入
        int rows = collectMapper.insert(collect);
        //结果封装
        log.info("CollectServiceImpl.save业务结束,结果:{}",rows);
        return R.ok("收藏添加成功!");
    }

    @Override
    public R list(Integer userId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.select("product_id");
        List<Object> idsObject = collectMapper.selectObjs(queryWrapper);
        ProductCollectParam productCollectParam = new ProductCollectParam();
        List<Integer> ids = new ArrayList<>();
        for (Object o : idsObject) {
            ids.add((Integer) o);
        }
        productCollectParam.setProductIds(ids);
        R r = productClient.productIds(productCollectParam);
        log.info("CollectServiceImpl.list业务结束,结果:{}",r);
        return r;
    }

    @Override
    public R remove(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",collect.getUserId());
        queryWrapper.eq("product_id",collect.getProductId());
        int rows = collectMapper.delete(queryWrapper);
        log.info("CollectServiceImpl.remove业务结束,结果:{}",rows);
        return R.ok("收藏移除成功!");
    }

    @Override
    public R removeByPid(Integer productId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        int rows = collectMapper.delete(queryWrapper);
        log.info("CollectServiceImpl.removeByPid业务结束,结果:{}",rows);
        return R.ok("收藏商品删除成功!");
    }


}
