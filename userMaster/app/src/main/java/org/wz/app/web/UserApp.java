package org.wz.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wz.app.service.UserAppCustomer;
import org.wz.config.bean.Page;
import org.wz.config.bean.User;
import org.wz.config.utils.JwtUtil;
import org.wz.config.utils.PageUtils;
import org.wz.config.utils.Response;


import java.util.*;

/**
 * @project: userManager
 * @package: com.wonders.service.impl
 * @author: Administrator
 * @crDate: 2019/3/20 10:47
 * @editor: IntelliJ IDEA
 * @role:
 **/
@RestController
public class UserApp {


    @Autowired
    UserAppCustomer userAppCustomer;

    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestParam Map<String, String> params){
        String token = JwtUtil.sign(params.get("phone"),params.get("password"));
        System.out.println("token--------------------------------"+token);
        return token;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/appList")
    public Response queryList(@RequestBody(required = false)Map<String, String> params) {
        List<User> users = new ArrayList<>();

        PageUtils.setPage(params);

        int count = 0;
        try {
            users = userAppCustomer.queryList(params);
            count = userAppCustomer.count(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Page page = new Page(users,count);

        return Response.page(page);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Response addUser(@RequestBody(required = false)User user) {
        user.setId(UUID.randomUUID().toString().replace("-",""));
        int i = userAppCustomer.addUser(user);

        return Response.operate(i==1);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.PUT)
    public Response deleteUser(@RequestBody Map<String,String> map) {
        int i = 0;
        try {
            i = userAppCustomer.deleteUser(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.operate(i==1);
    }

    @RequestMapping(value = "/queryInfo",method = RequestMethod.GET)
    public Response queryInfo(String id) {
        User user = null;
        try {
            user = userAppCustomer.queryInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.data(user);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Response editUser(@RequestBody(required = false)User user) {
        int i = 0;
        try {
            i = userAppCustomer.editUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.operate(i==1);
    }
}
