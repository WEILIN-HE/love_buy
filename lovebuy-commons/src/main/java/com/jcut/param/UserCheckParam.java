package com.jcut.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/11 21:51
 **/
@Data
public class UserCheckParam {

    @NotBlank
    private String userName;
}
