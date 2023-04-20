package com.jcut.param;

import com.jcut.pojo.Product;
import lombok.Data;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/29 16:55
 * 商品数据保存
 **/
@Data
public class ProductSaveParam extends Product {

    private String pictures;
}
