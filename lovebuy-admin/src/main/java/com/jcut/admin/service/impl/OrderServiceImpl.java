package com.jcut.admin.service.impl;

import com.jcut.admin.service.OrderService;
import com.jcut.clients.OrderClient;
import com.jcut.param.PageParam;
import com.jcut.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/29 22:06
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderClient orderClient;
    @Override
    public R list(PageParam pageParam) {
        R r = orderClient.list(pageParam);
        log.info("OrderServiceImpl.list业务结束,结果:{}",r);
        return r;
    }
}
