package com.jcut.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcut.param.OrderParam;
import com.jcut.param.PageParam;
import com.jcut.pojo.Order;
import com.jcut.utils.R;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/22 21:36
 **/
public interface OrderService extends IService<Order> {
    R save(OrderParam orderParam);

    R list(Integer userId);

    R check(Integer productId);

    R adminList(PageParam pageParam);
}
