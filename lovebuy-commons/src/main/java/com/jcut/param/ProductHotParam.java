package com.jcut.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/14 21:05
 **/
@Data
public class ProductHotParam {
    @NotEmpty
    private List<String> categoryName;
}
