package com.jcut.clients;

import com.jcut.param.PageParam;
import com.jcut.param.ProductHotParam;
import com.jcut.pojo.Category;
import com.jcut.pojo.Product;
import com.jcut.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/13 21:50
 **/
@FeignClient("category-service")
public interface CategoryClient {
    @GetMapping("/category/promo/{categoryName}")
    R byName(@PathVariable String categoryName);
    @PostMapping("/category/hots")
    R hots(@RequestBody ProductHotParam productHotParam);
    @GetMapping("/category/list")
    R list();
    @PostMapping("/category/admin/list")
    R adminPageList(@RequestBody PageParam pageParam);
    @PostMapping("/category/admin/save")
    R adminSave(@RequestBody Category category);
    @PostMapping("/category/admin/remove")
    R adminRemove(@RequestBody Integer categoryId);
    @PostMapping("/category/admin/update")
    R adminUpdate(@RequestBody Category category);
}
