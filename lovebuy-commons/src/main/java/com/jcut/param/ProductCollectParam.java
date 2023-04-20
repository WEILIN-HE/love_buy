package com.jcut.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/21 21:29
 **/
@Data
public class ProductCollectParam {
    @NotEmpty
    private List<Integer> productIds;
}
