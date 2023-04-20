package com.jcut.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jcut.clients.*;
import com.jcut.param.*;
import com.jcut.pojo.Picture;
import com.jcut.pojo.Product;
import com.jcut.product.mapper.PictureMapper;
import com.jcut.product.mapper.ProductMapper;
import com.jcut.product.service.ProductService;
import com.jcut.to.OrderToProduct;
import com.jcut.utils.R;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.bouncycastle.util.io.pem.PemObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/13 21:58
 **/
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private SearchClient searchClient;
    @Autowired
    private OrderClient orderClient;
    @Autowired
    private CartClient cartClient;
    @Autowired
    private CollectClient collectClient;

    @Cacheable(value = "product", key = "#productID")
    @Override
    public R detail(Integer productID) {
        Product product = productMapper.selectById(productID);
        R ok = R.ok(product);
        log.info("ProductServiceImpl.detail业务结束,结果:{}", ok);
        return ok;
    }

    @Cacheable(value = "list.product", key = "#categoryName", cacheManager = "cacheManagerDay")
    @Override
    public R promo(String categoryName) {
        R r = categoryClient.byName(categoryName);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.promo业务结束,结果:{}", "类别查询失败!");
            return r;
        }
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) r.getData();
        Integer categoryId = (Integer) map.get("category_id");
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1, 7);
        page = productMapper.selectPage(page, queryWrapper);
        List<Product> productList = page.getRecords();
        long total = page.getTotal();
        log.info("ProductServiceImpl.promo业务结束,结果:{}", productList);
        return R.ok("数据查询成功", productList);
    }

    @Cacheable(value = "list.product", key = "#productHotParam.categoryName")
    @Override
    public R hots(ProductHotParam productHotParam) {
        R r = categoryClient.hots(productHotParam);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.hots业务结束,结果:{}", r.getMsg());
            return r;
        }
        List<Object> ids = (List<Object>) r.getData();
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id", ids);
        queryWrapper.orderByDesc("product_sales");
        IPage<Product> page = new Page<>(1, 7);
        page = productMapper.selectPage(page, queryWrapper);
        List<Product> records = page.getRecords();
        R ok = R.ok("多类别热门商品查询成功!", records);
        log.info("ProductServiceImpl.hots业务结束,结果:{}", ok);
        return ok;
    }

    @Override
    public R clist() {
        R r = categoryClient.list();
        log.info("ProductServiceImpl.clist业务结束,结果:{}", r);
        return r;
    }

    @Cacheable(value = "list.product", key = "#productIdsParam.categoryID+'-'+#productIdsParam.currentPage+'-'+#productIdsParam.pageSize")
    @Override
    public R byCategory(ProductIdsParam productIdsParam) {

        List<Integer> categoryID = productIdsParam.getCategoryID();
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (!categoryID.isEmpty()) {
            queryWrapper.in("category_id", categoryID);
        }
        IPage<Product> page = new Page<>(productIdsParam.getCurrentPage(), productIdsParam.getPageSize());
        page = productMapper.selectPage(page, queryWrapper);
        R ok = R.ok("查询成功!", page.getRecords(), page.getTotal());
        log.info("ProductServiceImpl.byCategory业务结束,结果:{}", ok);
        return ok;
    }


    @Cacheable(value = "picture", key = "#productID")
    @Override
    public R pictures(Integer productID) {
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productID);
        List<Picture> pictureList = pictureMapper.selectList(queryWrapper);
        R ok = R.ok(pictureList);
        log.info("ProductServiceImpl.pictures业务结束,结果:{}", ok);
        return ok;
    }

    @Cacheable(value = "List.category", key = "#root.methodName", cacheManager = "cacheManagerDay")
    @Override
    public List<Product> allList() {
        List<Product> productList = productMapper.selectList(null);
        log.info("ProductServiceImpl.allList业务结束,结果:{}", productList.size());
        return productList;
    }

    @Override
    public R search(ProductSearchParam productSearchParam) {
        R r = searchClient.search(productSearchParam);
        log.info("ProductServiceImpl.search业务结束,结果:{}", r);
        return r;
    }

    @Cacheable(value = "list.product", key = "#productIds")
    @Override
    public R ids(List<Integer> productIds) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id", productIds);
        List<Product> productList = productMapper.selectList(queryWrapper);
        R r = R.ok("类别信息查询成功!", productList);
        log.info("ProductServiceImpl.ids业务结束,结果:{}", r);
        return r;
    }

    @Override
    public List<Product> cartList(List<Integer> productIds) {

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id", productIds);
        List<Product> productList = productMapper.selectList(queryWrapper);
        log.info("ProductServiceImpl.cartList业务结束,结果:{}", productList);
        return productList;
    }

    @Override
    public void subNumber(List<OrderToProduct> orderToProducts) {
        Map<Integer, OrderToProduct> map = orderToProducts.stream().collect(Collectors.toMap(OrderToProduct::getProductId, v -> v));
        Set<Integer> productIds = map.keySet();
        List<Product> productList = productMapper.selectBatchIds(productIds);
        for (Product product : productList) {
            Integer num = map.get(product.getProductId()).getNum();
            product.setProductNum(product.getProductNum() - num);//减库存
            product.setProductSales(product.getProductSales() + num);//加销售量
        }
        this.updateBatchById(productList);
        log.info("ProductServiceImpl.subNumber业务结束,结果:库存和销售量的修改完毕");
    }

    @Override
    public Long adminCount(Integer categoryId) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        Long count = baseMapper.selectCount(queryWrapper);
        log.info("ProductServiceImpl.adminCount业务结束,结果:{}", count);
        return count;
    }

    @CacheEvict(value = "list.product", allEntries = true)
    @Override
    public R adminSave(ProductSaveParam productSaveParam) {

        Product product = new Product();
        BeanUtils.copyProperties(productSaveParam, product);

        int rows = productMapper.insert(product);
        log.info("ProductServiceImpl.adminSave业务结束,结果:{}", rows);
        String pictures = productSaveParam.getPictures();
        if (!StringUtils.isEmpty(pictures)) {
            String[] urls = pictures.split("\\+");
            List<Picture> pictureList = new ArrayList<>();
            for (String url : urls) {
                Picture picture = new Picture();
                picture.setProductId(product.getProductId());
                picture.setProductPicture(url);
                pictureMapper.insert(picture);
            }
        }
        searchClient.saveOrUpdate(product);
        return R.ok("商品数据添加成功!");
    }

    @Override
    public R adminUpdate(Product product) {
        productMapper.updateById(product);
        searchClient.saveOrUpdate(product);
        return R.ok("商品数据更新成功!");
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "product.list",allEntries = true),
                    @CacheEvict(value = "product",key = "#productId")
            }
    )
    @Override
    public R adminRemove(Integer productId) {
        R r = cartClient.check(productId);
        if ("004".equals(r.getCode())) {
            log.info("ProductServiceImpl.adminRemove业务结束,结果:{}", r.getMsg());
            return r;
        }
        r = orderClient.check(productId);
        if ("004".equals(r.getCode())) {
            log.info("ProductServiceImpl.adminRemove业务结束,结果:{}", r.getMsg());
            return r;
        }
        productMapper.deleteById(productId);
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        pictureMapper.delete(queryWrapper);
        collectClient.remove(productId);
        searchClient.remove(productId);
        return R.ok("商品删除成功!");
    }
}
