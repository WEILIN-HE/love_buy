package com.jcut.search.controller;

import com.jcut.param.ProductSearchParam;
import com.jcut.pojo.Product;
import com.jcut.search.service.SearchService;
import com.jcut.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/15 19:48
 **/
@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    private SearchService searchService;
    @PostMapping("product")
    public R searchProduct(@RequestBody ProductSearchParam productSearchParam){
        return searchService.search(productSearchParam);
    }
    //同步调用进行商品插入，覆盖更新
    @PostMapping("save")
    public R saveProduct(@RequestBody Product product) throws IOException {
        return searchService.save(product);
    }
    @PostMapping("remove")
    public R removeProduct(@RequestBody Integer productId) throws IOException {
        return searchService.remove(productId);
    }
}
