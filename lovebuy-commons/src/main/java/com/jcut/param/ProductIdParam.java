package com.jcut.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/15 10:12
 **/
@Data
public class ProductIdParam {
    @NotNull
    private Integer productID;
}
