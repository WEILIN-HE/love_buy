package com.jcut.clients;

import com.jcut.param.ProductCollectParam;
import com.jcut.param.ProductIdParam;
import com.jcut.param.ProductIdsParam;
import com.jcut.param.ProductSaveParam;
import com.jcut.pojo.Product;
import com.jcut.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/15 11:46
 **/
@FeignClient(value = "product-service")
public interface ProductClient {
    @GetMapping("/product/list")
    List<Product> allList();

    @PostMapping("/product/collect/list")
    R productIds(@RequestBody ProductCollectParam productCollectParam);

    @PostMapping("/product/cart/detail")
    Product productDetail(@RequestBody ProductIdParam productIdParam);

    @PostMapping("/product/cart/list")
    List<Product> cartList(@RequestBody ProductCollectParam productCollectParam);

    @PostMapping("/product/admin/count")
    Long adminCount(@RequestBody Integer categoryId);
    @PostMapping("/product/admin/save")
    R adminCount(@RequestBody ProductSaveParam productSaveParam);
    @PostMapping("/product/admin/update")
    R adminUpdate(@RequestBody Product product);
    @PostMapping("/product/admin/remove")
    R adminRemove(@RequestBody Integer productId);
}


