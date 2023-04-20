package com.jcut.product.listener;

import com.jcut.product.service.ProductService;
import com.jcut.to.OrderToProduct;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/22 22:20
 **/
@Component
public class ProductRabbitMqListener {
    @Autowired
    private ProductService productService;
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "sub.queue"),
                    exchange = @Exchange("topic.ex"),
                    key = "sub.number"
            )
    )
    public void subNumber(List<OrderToProduct> orderToProducts){
        productService.subNumber(orderToProducts);
    }
}
