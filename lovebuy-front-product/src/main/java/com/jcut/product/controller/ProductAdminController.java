package com.jcut.product.controller;

import com.jcut.param.ProductSaveParam;
import com.jcut.pojo.Product;
import com.jcut.product.service.ProductService;
import com.jcut.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/29 14:39
 **/
@RestController
@RequestMapping("product")
public class ProductAdminController {
    @Autowired
    private ProductService productService;
    @PostMapping("/admin/count")
    public Long adminCount(@RequestBody Integer categoryId){

        return productService.adminCount(categoryId);
    }
    @PostMapping("/admin/save")
    public R adminSave(@RequestBody ProductSaveParam productSaveParam){

        return productService.adminSave(productSaveParam);
    }
    @PostMapping("/admin/update")
    public R adminUpdate(@RequestBody Product product){
        return productService.adminUpdate(product);
    }
    @PostMapping("/admin/remove")
    public R adminRemove(@RequestBody Integer productId){

        return productService.adminRemove(productId);
    }
}
