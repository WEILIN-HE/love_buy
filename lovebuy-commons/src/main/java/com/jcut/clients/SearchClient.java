package com.jcut.clients;

import com.jcut.param.ProductSearchParam;
import com.jcut.pojo.Product;
import com.jcut.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/15 20:31
 **/
@FeignClient("search-service")
public interface SearchClient {
    @PostMapping("/search/product")
    R search(@RequestBody ProductSearchParam productSearchParam);
    @PostMapping("/search/save")
    R saveOrUpdate(@RequestBody Product product);
    @PostMapping("/search/remove")
    R remove(@RequestBody Integer productId);
}
