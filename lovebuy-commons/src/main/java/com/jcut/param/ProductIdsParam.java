package com.jcut.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/14 22:05
 **/
@Data
public class ProductIdsParam extends PageParam{
    @NotNull
    private List<Integer> categoryID;

}
