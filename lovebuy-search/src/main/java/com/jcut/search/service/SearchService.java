package com.jcut.search.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jcut.param.ProductSearchParam;
import com.jcut.pojo.Product;
import com.jcut.utils.R;

import java.io.IOException;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/15 19:53
 **/
public interface SearchService {
    R search(ProductSearchParam productSearchParam);

    R save(Product product) throws IOException;

    R remove(Integer productId) throws IOException;
}
