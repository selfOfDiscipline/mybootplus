package com.zwc.controller.commoncontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProJectName: bootplus
 * @Author: zwc  zwc_503@163.com
 * @CreateTime: 2019-09-11 11:15
 * @Description: //TODO 页面跳转方法类
 **/
@Controller
public class ViewController {

    @RequestMapping(value = "/")
    public String toIndex() {
        return "index";
    }
}
