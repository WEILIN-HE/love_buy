package com.jcut.admin.controller;

import com.jcut.admin.service.OrderService;
import com.jcut.param.PageParam;
import com.jcut.utils.R;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/29 22:03
 **/
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/list")
    public R list(PageParam pageParam){
        return orderService.list(pageParam);
    }
}
