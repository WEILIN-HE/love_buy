package com.jcut.to;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/22 21:29
 **/
@Data
public class OrderToProduct implements Serializable {
    public  static final Long serialVersionUID = 1L;
    private Integer productId;
    private Integer num;

}
