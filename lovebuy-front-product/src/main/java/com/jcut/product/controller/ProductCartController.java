package com.jcut.product.controller;

import com.jcut.param.ProductCollectParam;
import com.jcut.param.ProductIdParam;
import com.jcut.pojo.Product;
import com.jcut.product.service.ProductService;
import com.jcut.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/22 16:08
 **/
@RestController
@RequestMapping("product")
public class ProductCartController {
    @Autowired
    private ProductService productService;
    @PostMapping("cart/detail")
    public Product cdetail(@RequestBody @Validated ProductIdParam productIdParam, BindingResult result){
        if(result.hasErrors()){
            return null;
        }
        R detail = productService.detail(productIdParam.getProductID());
        Product product = (Product) detail.getData();
        return product;
    }
    @PostMapping("cart/list")
    public List<Product> cartList(@RequestBody @Validated ProductCollectParam productCollectParam,BindingResult result){
        if(result.hasErrors()){
            return new ArrayList<Product>();
        }
        return productService.cartList(productCollectParam.getProductIds());
    }
}
