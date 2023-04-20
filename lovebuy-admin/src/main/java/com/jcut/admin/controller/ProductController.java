package com.jcut.admin.controller;

import com.jcut.admin.service.ProductService;
import com.jcut.param.ProductSaveParam;
import com.jcut.param.ProductSearchParam;
import com.jcut.pojo.Product;
import com.jcut.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/29 15:14
 **/
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public R adminList(ProductSearchParam productSearchParam){
        return productService.search(productSearchParam);
    }

    @PostMapping("save")
    public R adminSave(ProductSaveParam productSaveParam){
        return productService.save(productSaveParam);
    }
    @PostMapping("update")
    public R adminUpdate(Product product){
        return productService.update(product);
    }
    @PostMapping("remove")
    public R adminRemove(Integer productId){
        return productService.remove(productId);
    }
}
