package com.jcut.admin.service;

import com.jcut.param.ProductSaveParam;
import com.jcut.param.ProductSearchParam;
import com.jcut.pojo.Product;
import com.jcut.utils.R;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/29 15:20
 **/
public interface ProductService {
    R search(ProductSearchParam productSearchParam);

    R save(ProductSaveParam productSaveParam);

    R update(Product product);

    R remove(Integer productId);
}
