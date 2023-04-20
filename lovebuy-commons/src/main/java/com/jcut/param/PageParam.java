package com.jcut.param;

import lombok.Data;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/15 19:42
 **/
@Data
public class PageParam {
    private int currentPage = 1;
    private int pageSize = 15;
}

