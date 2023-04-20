package com.jcut.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcut.clients.ProductClient;
import com.jcut.order.mapper.OrderMapper;
import com.jcut.order.service.OrderService;
import com.jcut.param.OrderParam;
import com.jcut.param.PageParam;
import com.jcut.param.ProductCollectParam;
import com.jcut.pojo.Order;
import com.jcut.pojo.Product;
import com.jcut.to.OrderToProduct;
import com.jcut.utils.R;
import com.jcut.vo.AdminOrderVo;
import com.jcut.vo.CartVo;
import com.jcut.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/22 21:42
 **/
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private ProductClient productClient;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderMapper orderMapper;
    @Transactional
    @Override
    public R save(OrderParam orderParam) {
        List<Integer> cartIds = new ArrayList<>();
        List<OrderToProduct> orderToProducts = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();
        Integer userId = orderParam.getUserId();
        long orderId = System.currentTimeMillis();
        for (CartVo cartVo : orderParam.getProducts()) {
            cartIds.add(cartVo.getId());
            OrderToProduct orderToProduct = new OrderToProduct();
            orderToProduct.setNum(cartVo.getNum());
            orderToProduct.setProductId(cartVo.getProductID());
            orderToProducts.add(orderToProduct);
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderTime(orderId);
            order.setUserId(userId);
            order.setProductId(cartVo.getProductID());
            order.setProductNum(cartVo.getNum());
            order.setProductPrice(cartVo.getPrice());
            orderList.add(order);
        }
        //订单数据批量保存
        saveBatch(orderList);

        rabbitTemplate.convertAndSend("topic.ex","clear.cart",cartIds);
        rabbitTemplate.convertAndSend("topic.ex","sub.number",orderToProducts);
        return R.ok("订单保存成功!");
    }

    @Override
    public R list(Integer userId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Order> list = list(queryWrapper);
        Map<Long, List<Order>> orderMap = list.stream().collect(Collectors.groupingBy(Order::getOrderId));
        List<Integer> productIds = list.stream().map(Order::getProductId).collect(Collectors.toList());
        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIds);
        List<Product> productList = productClient.cartList(productCollectParam);
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));
        List<List<OrderVo>> result = new ArrayList<>();
        for (List<Order> orders : orderMap.values()) {
            List<OrderVo> orderVos = new ArrayList<>();
            for (Order order : orders) {
                OrderVo orderVo = new OrderVo();
                BeanUtils.copyProperties(order,orderVo);
                Product product = productMap.get(order.getProductId());
                orderVo.setProductName(product.getProductName());
                orderVo.setProductPicture(product.getProductPicture());
                orderVos.add(orderVo);
            }
            result.add(orderVos);
        }
        R r = R.ok("订单数据获取成功!", result);
        log.info("OrderServiceImpl.list业务结束,结果:{}",r);
        return r;
    }

    @Override
    public R check(Integer productId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        Long count = baseMapper.selectCount(queryWrapper);
        if(count >0){
            return R.fail("订单:"+count+"项引用该商品,不能删除!");
        }
        return R.ok("无订单引用,可以删除");
    }

    @Override
    public R adminList(PageParam pageParam) {
        int offset = (pageParam.getCurrentPage()-1)*pageParam.getPageSize();
        int pageSize = pageParam.getPageSize();
        List<AdminOrderVo> adminOrderVoList =  orderMapper.selectAdminOrder(offset, pageSize);

        return R.ok("订单数据查询成功!",adminOrderVoList);
    }
}
