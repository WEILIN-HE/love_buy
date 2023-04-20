package com.jcut.clients;

import com.jcut.param.CartListParam;
import com.jcut.param.PageParam;
import com.jcut.pojo.User;
import com.jcut.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/27 21:30
 **/
@FeignClient("user-service")
public interface UserClient {
    @PostMapping("/user/admin/list")
    R adminListPage(@RequestBody PageParam pageParam);
    @PostMapping("/user/admin/remove")
    R adminRemove(@RequestBody CartListParam cartListParam);
    @PostMapping("/user/admin/update")
    R adminUpdate(@RequestBody User user);
    @PostMapping("/user/admin/save")
    R adminSave(@RequestBody User user);
}
