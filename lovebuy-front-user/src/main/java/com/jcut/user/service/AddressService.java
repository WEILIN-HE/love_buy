package com.jcut.user.service;

import com.jcut.pojo.Address;
import com.jcut.utils.R;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/12 19:02
 **/
public interface AddressService {

    R list(Integer userId);

    R save(Address address);

    R remove(Integer id);
}
