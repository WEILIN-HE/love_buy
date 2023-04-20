package com.jcut.clients;

import com.jcut.param.PageParam;
import com.jcut.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/29 21:09
 **/
@FeignClient("order-service")
public interface OrderClient {
    @PostMapping("/order/remove/check")
    R check(@RequestBody Integer productId);
    @PostMapping("/order/admin/list")
    R list(@RequestBody PageParam pageParam);
}
