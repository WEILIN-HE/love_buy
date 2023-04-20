package com.jcut.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jcut.vo.CartVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/22 21:26
 **/
@Data
public class OrderParam implements Serializable {
    public static final Long serialVersionUID = 1L;
    @JsonProperty("user_id")
    private Integer userId;
    private List<CartVo> products;
}
