package com.jcut.category.service;

import com.jcut.param.PageParam;
import com.jcut.param.ProductHotParam;
import com.jcut.pojo.Category;
import com.jcut.utils.R;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/13 21:36
 **/
public interface CategoryService {
    R byName(String categoryName);

    R hotsCategory(ProductHotParam productHotParam);

    R list();

    R listPage(PageParam pageParam);

    R adminSave(Category category);

    R adminRemove(Integer categoryId);

    R adminUpdate(Category category);
}
