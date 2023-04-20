package com.jcut.doc;

import com.jcut.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/15 14:26
 **/
@Data
@NoArgsConstructor
public class ProductDoc extends Product {
    private String all;
    public ProductDoc(Product product){
        super(product.getProductId(),product.getProductName(),product.getCategoryId(),
                product.getProductTitle(),product.getProductIntro(),product.getProductPicture(),
                product.getProductPrice(),product.getProductSellingPrice(),product.getProductNum(),
                product.getProductSales());
        this.all = product.getProductName()+product.getProductTitle()+product.getProductIntro();
    }
}
