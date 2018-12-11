package com.wz.mqtest.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/12/10 0010.
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public String index(){
        return "Hello Worlda";
    }
}
