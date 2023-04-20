package com.jcut.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jcut.pojo.Order;
import com.jcut.vo.AdminOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/22 21:41
 **/
public interface OrderMapper extends BaseMapper<Order> {
    //查询后台管理的数据方法
    List<AdminOrderVo> selectAdminOrder(@Param("offset") int offset,@Param("pageSize") int pageSize);
}
