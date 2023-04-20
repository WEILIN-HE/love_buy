package com.jcut.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/12 10:54
 **/
@Data
public class UserLoginParam {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
