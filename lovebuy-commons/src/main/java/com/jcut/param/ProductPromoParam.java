package com.jcut.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/13 21:27
 **/
@Data
public class ProductPromoParam{
    @NotBlank
    private String categoryName;
}
