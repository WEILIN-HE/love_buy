package com.jcut.admin.controller;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:何炜霖
 * @DateTime: 2023/3/27 20:05
 **/
@Controller
@RequestMapping
public class CaptchaController {
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //自动生成验证码图片
        CaptchaUtil.out(request,response);
    }
}
