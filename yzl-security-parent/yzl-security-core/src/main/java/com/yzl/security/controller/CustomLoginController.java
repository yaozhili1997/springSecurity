package com.yzl.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 姚志立
 * @Date: 2020/5/2 19:53
 * @Version: 1.0
 */

@Controller
public class CustomLoginController {
    @RequestMapping("/login/page")
    public String toLogin(){
        return "login";//class:/template/login.html
    }
}
