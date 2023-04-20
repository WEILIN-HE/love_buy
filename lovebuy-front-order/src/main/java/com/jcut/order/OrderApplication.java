package com.jcut.order;

import com.jcut.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/22 21:12
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.jcut.order.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
