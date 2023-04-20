package com.jcut.admin.controller;

import com.jcut.admin.service.CategoryService;
import com.jcut.param.PageParam;
import com.jcut.pojo.Category;
import com.jcut.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/29 11:48
 **/
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public R pageList(PageParam pageParam) {
        return categoryService.pageList(pageParam);
    }

    @PostMapping("save")
    public R save(Category category) {
        return categoryService.save(category);
    }
    @PostMapping("remove")
    public R remove(Integer categoryId) {
        return categoryService.remove(categoryId);
    }
    @PostMapping("update")
    public R update(Category category) {
        return categoryService.update(category);
    }
}
