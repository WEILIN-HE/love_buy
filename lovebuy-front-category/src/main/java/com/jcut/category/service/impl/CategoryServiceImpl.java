package com.jcut.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jcut.category.CategoryApplication;
import com.jcut.category.mapper.CategoryMapper;
import com.jcut.category.service.CategoryService;
import com.jcut.clients.ProductClient;
import com.jcut.param.PageParam;
import com.jcut.param.ProductHotParam;
import com.jcut.pojo.Category;
import com.jcut.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/13 21:37
 **/
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductClient productClient;
    @Override
    public R byName(String categoryName) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name",categoryName);
        Category category = categoryMapper.selectOne(categoryQueryWrapper);
        if(category == null){
            log.info("CategoryServiceImpl.byName业务结束,结果:类别查询失败");
            return R.fail("类别查询失败!");
        }
        log.info("CategoryServiceImpl.byName业务结束,结果:{}","类别查询成功!");
        return R.ok("类别查询成功!",category);
    }

    @Override
    public R hotsCategory(ProductHotParam productHotParam) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_name",productHotParam.getCategoryName());
        queryWrapper.select("category_id");
        List<Object> ids = categoryMapper.selectObjs(queryWrapper);
        R ok = R.ok("类别集合查询成功", ids);
        log.info("CategoryServiceImpl.hotsCategory业务结束,结果:{}",ok);
        return ok;
    }

    @Override
    public R list() {
        List<Category> categoryList = categoryMapper.selectList(null);
        R ok = R.ok("类别集合全部数据查询成功!", categoryList);
        log.info("CategoryServiceImpl.list业务结束,结果:{}",ok);
        return ok;
    }

    @Override
    public R listPage(PageParam pageParam) {
        IPage<Category> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        page = categoryMapper.selectPage(page,null);
        return R.ok("分页数据查询成功!",page.getRecords(),page.getTotal());
    }

    @Override
    public R adminSave(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name",category.getCategoryName());
        Long count = categoryMapper.selectCount(queryWrapper);
        if(count >0){
            return R.fail("类别已经存在，添加失败!");
        }
        int insert = categoryMapper.insert(category);
        log.info("CategoryServiceImpl.adminSave业务结束,结果:{}",insert);
        return R.ok("类别添加成功!");
    }

    @Override
    public R adminRemove(Integer categoryId) {

        Long aLong = productClient.adminCount(categoryId);
        if(aLong >0){
            return R.fail("类别删除失败,有:"+aLong+"件商品正在引用!");
        }
        int i = categoryMapper.deleteById(categoryId);
        log.info("CategoryServiceImpl.adminRemove业务结束,结果:{}",i);
        return R.ok("类别数据删除成功!");
    }

    @Override
    public R adminUpdate(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name",category.getCategoryName());
        Long count = categoryMapper.selectCount(queryWrapper);
        if(count >0){
            return R.fail("类别已经存在，修改失败!");
        }
        int i = categoryMapper.updateById(category);
        log.info("CategoryServiceImpl.adminUpdate业务结束,结果:{}",i);
        return R.ok("类别数据修改成功!");
    }
}
