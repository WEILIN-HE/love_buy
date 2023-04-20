package com.jcut.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * projectName: xmstore
 *
 * @author: 何炜霖
 * time: 2022/3/24 22:29 周四
 * description: 返回结果通用对象  Map
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R  implements Serializable {
    public static final Long serialVersionUID = 1L;

    public static final String SUCCESS_CODE = "001";

    public static final String FAIL_CODE = "004";

    public static final String USER_NO_LOGIN = "401";

    private String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long   total;

    public static R ok(String msg,Object data,Long total){

        return new R(SUCCESS_CODE,msg,data,total);
    }

    public static R ok(String msg,Object data){

        return ok(msg,data,null);
    }

    public static R ok(String msg){

        return ok(msg,null);
    }

    public static R ok(Object data){

        return ok(null,data);
    }

    public static R fail(String msg,Object data,Long total){

        return new R(FAIL_CODE,msg,data,total);
    }
    public static R fail(String msg,Object data){

        return fail(msg,data,null);
    }
    public static R fail(String msg){

        return fail(msg,null);
    }
    public static R fail(Object data){

        return fail(null,data);
    }
    public static R NO_LOGIN(){

        return fail(USER_NO_LOGIN,"用户未登录!");
    }
}
