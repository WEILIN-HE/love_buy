package com.jcut.cart.service;

import com.jcut.param.CartSaveParam;
import com.jcut.pojo.Cart;
import com.jcut.utils.R;

import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/22 16:27
 **/
public interface CartService {
    R save(CartSaveParam cartSaveParam);

    R list(Integer userId);

    R update(Cart cart);

    R remove(Cart cart);

    void clearIds(List<Integer> cartIds);

    R check(Integer productId);
}
