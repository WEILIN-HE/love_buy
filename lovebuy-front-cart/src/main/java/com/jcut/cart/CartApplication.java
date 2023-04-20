package com.jcut.cart;

import com.jcut.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/22 15:51
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.jcut.cart.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class);
    }
}
