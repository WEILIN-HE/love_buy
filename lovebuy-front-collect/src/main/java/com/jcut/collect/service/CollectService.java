package com.jcut.collect.service;

import com.jcut.pojo.Collect;
import com.jcut.utils.R;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/20 22:03
 **/
public interface CollectService {
    R save(Collect collect);

    R list(Integer userId);

    R remove(Collect collect);

    R removeByPid(Integer productId);
}
