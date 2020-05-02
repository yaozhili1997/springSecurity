package com.yzl.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 姚志立
 * @Date: 2020/5/2 17:26
 * @Version: 1.0
 */

@Controller
public class MainController {
    @RequestMapping({"/index","/",""})
    public String index(){
        return "index";
    }
}
