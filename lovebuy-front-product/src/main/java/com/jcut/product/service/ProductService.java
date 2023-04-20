package com.jcut.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jcut.param.ProductHotParam;
import com.jcut.param.ProductIdsParam;
import com.jcut.param.ProductSaveParam;
import com.jcut.param.ProductSearchParam;
import com.jcut.pojo.Product;
import com.jcut.to.OrderToProduct;
import com.jcut.utils.R;

import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/13 21:58
 **/

public interface ProductService extends IService<Product> {

    R detail(Integer productID);

    R promo(String categoryName);

    R hots(ProductHotParam productHotParam);

    R clist();

    R byCategory(ProductIdsParam productIdsParam);

    R pictures(Integer productID);

    List<Product> allList();

    R search(ProductSearchParam productSearchParam);


    R ids(List<Integer> productIds);

    List<Product> cartList(List<Integer> productIds);

    void subNumber(List<OrderToProduct> orderToProducts);

    Long adminCount(Integer categoryId);

    R adminSave(ProductSaveParam productSaveParam);

    R adminUpdate(Product product);

    R adminRemove(Integer productId);
}
