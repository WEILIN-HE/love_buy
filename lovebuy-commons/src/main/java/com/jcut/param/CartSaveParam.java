package com.jcut.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/22 16:20
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartSaveParam {
    @NotNull
    @JsonProperty("product_id")
    private Integer productId;
    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
}
