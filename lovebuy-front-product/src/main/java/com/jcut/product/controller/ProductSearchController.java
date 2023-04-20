package com.jcut.product.controller;

import com.jcut.pojo.Product;
import com.jcut.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/15 11:41
 **/
@RestController
@RequestMapping("product")
public class ProductSearchController {
    @Autowired
    private ProductService productService;
    @GetMapping("list")
    public List<Product> allList(){
        return productService.allList();

    }
}
