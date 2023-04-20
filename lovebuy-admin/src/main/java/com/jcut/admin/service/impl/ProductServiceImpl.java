package com.jcut.admin.service.impl;

import com.jcut.admin.service.ProductService;
import com.jcut.clients.ProductClient;
import com.jcut.clients.SearchClient;
import com.jcut.param.ProductSaveParam;
import com.jcut.param.ProductSearchParam;
import com.jcut.pojo.Product;
import com.jcut.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/29 15:22
 **/
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private SearchClient searchClient;
    @Autowired
    private ProductClient productClient;
    @Override
    public R search(ProductSearchParam productSearchParam) {
        R search = searchClient.search(productSearchParam);
        log.info("ProductServiceImpl.search业务结束,结果:{}",search);
        return search;
    }

    @Override
    public R save(ProductSaveParam productSaveParam) {
        R r = productClient.adminCount(productSaveParam);
        log.info("ProductServiceImpl.save业务结束,结果:{}",r);
        return r;
    }

    @Override
    public R update(Product product) {
        R r = productClient.adminUpdate(product);
        log.info("ProductServiceImpl.update业务结束,结果:{}",r);
        return r;
    }

    @Override
    public R remove(Integer productId) {
        R r = productClient.adminRemove(productId);
        log.info("ProductServiceImpl.remove业务结束,结果:{}",r);
        return r;
    }
}
