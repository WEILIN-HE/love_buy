package com.jcut.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/22 19:15
 **/
@Data
public class CartListParam {
    @JsonProperty("user_id")
    @NotNull
    private Integer userId;
}
