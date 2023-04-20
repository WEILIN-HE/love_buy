package com.jcut.category.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jcut.category.service.CategoryService;
import com.jcut.param.ProductHotParam;
import com.jcut.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/13 21:33
 **/
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/promo/{categoryName}")
    public R byName(@PathVariable String categoryName){
        if(StringUtils.isEmpty(categoryName)){
            return R.fail("类别名称为null,无法查询类别数据!");
        }
        return categoryService.byName(categoryName);
    }

    @PostMapping("hots")
    public R hotsCategory(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result){
        if(result.hasErrors()){
            return R.fail("类别集合查询失败!");
        }
        return categoryService.hotsCategory(productHotParam);
    }

    @GetMapping("list")
    public R list(){
        return categoryService.list();
    }
}

