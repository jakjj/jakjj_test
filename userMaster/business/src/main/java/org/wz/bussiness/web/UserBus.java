package org.wz.bussiness.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wz.config.bean.User;
import org.wz.bussiness.service.UserBusCustomer;
import org.wz.config.utils.JwtUtil;
import org.wz.config.utils.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @project: userMaster
 * @package: org.wz.bussiness.service
 * @author: Administrator
 * @crDate: 2019/3/21 14:46
 * @editor: IntelliJ IDEA
 * @role:
 **/
@RestController
@RequestMapping("/user")
public class UserBus {

    @Autowired
    UserBusCustomer userBusCustomer;

    @RequestMapping(method = RequestMethod.GET,value = "/list")
    public Response list(@RequestParam(required = false) Map<String, String> params){

        if(params == null){
            params = new HashMap<>();
        }
        Response users = userBusCustomer.queryList(params);

        System.out.print(users.get("page"));
        return users;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public Response add(@RequestBody(required = false)User user){

        return userBusCustomer.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/delete")
    public Response delete(@RequestBody Map<String,String> map){

        return userBusCustomer.deleteUser(map);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/edit")
    public Response edit(@RequestBody(required = false)User user){

        return userBusCustomer.editUser(user);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/queryInfo")
    public Response queryInfo(String id){

        return userBusCustomer.queryInfo(id);
    }
}
