package com.jcut.admin.service;

import com.jcut.param.PageParam;
import com.jcut.pojo.Category;
import com.jcut.utils.R;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/29 11:50
 **/
public interface CategoryService {
    R pageList(PageParam pageParam);

    R save(Category category);

    R remove(Integer categoryId);

    R update(Category category);
}
