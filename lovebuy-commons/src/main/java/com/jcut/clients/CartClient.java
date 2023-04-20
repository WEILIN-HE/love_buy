package com.jcut.clients;

import com.jcut.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/29 21:03
 **/
@FeignClient("cart-service")
public interface CartClient {
    @PostMapping("/cart/remove/check")
    R check(@RequestBody Integer productId);
}
