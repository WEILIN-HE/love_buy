package com.jcut.cart.controller;

import com.jcut.cart.service.CartService;
import com.jcut.param.CartListParam;
import com.jcut.param.CartSaveParam;
import com.jcut.pojo.Cart;
import com.jcut.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/22 16:23
 **/
@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("save")
    public R save(@RequestBody @Validated CartSaveParam cartSaveParam, BindingResult result){
        if(result.hasErrors()){
            return R.fail("核心参数为null,添加失败!");
        }
        return cartService.save(cartSaveParam);
    }

    @PostMapping("list")
    public R list(@RequestBody @Validated CartListParam cartListParam,BindingResult result){
        if(result.hasErrors()){
            return R.fail("购物车数据查询失败!");
        }

        return cartService.list(cartListParam.getUserId());
    }
    @PostMapping("update")
    public R update(@RequestBody Cart cart){
        return cartService.update(cart);
    }
    @PostMapping("remove")
    public R remove(@RequestBody Cart cart){
        return cartService.remove(cart);
    }
    @PostMapping("remove/check")
    public R removeClick(@RequestBody Integer productId){

        return cartService.check(productId);
    }
}
