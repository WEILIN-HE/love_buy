package com.jcut.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jcut.pojo.Address;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/24 21:47
 **/
@Data
public class AddressParam {
    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
    private Address add;
}
