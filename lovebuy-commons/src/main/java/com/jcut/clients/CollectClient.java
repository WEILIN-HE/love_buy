package com.jcut.clients;

import com.jcut.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/29 21:24
 **/
@FeignClient("collect-service")
public interface CollectClient {
    @PostMapping("/collect/remove/product")
    R remove(@RequestBody Integer productId);
}
