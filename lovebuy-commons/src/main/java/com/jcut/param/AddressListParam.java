package com.jcut.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/12 18:54
 **/
@Data
public class AddressListParam {
    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
}
