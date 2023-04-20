package com.jcut.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/12 19:24
 **/
@Data
public class AddressRemoveParam {
    @NotNull
    private Integer id;
}
