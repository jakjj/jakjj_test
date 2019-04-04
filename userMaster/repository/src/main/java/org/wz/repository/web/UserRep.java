package org.wz.repository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wz.repository.dao.UserDao;
import org.wz.config.bean.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project: userMaster
 * @package: org.wz.repository.web
 * @author: Administrator
 * @crDate: 2019/3/22 9:34
 * @editor: IntelliJ IDEA
 * @role:
 **/

@RestController
public class UserRep {

    @Autowired
    UserDao userDao;

    @RequestMapping(method = RequestMethod.GET,value = "/repList")
    public List<User> queryList(@RequestBody(required = false) Map<String, String> params){
        List<User> users = new ArrayList<>();
        try {
            users = userDao.queryList(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public int add(@RequestBody(required = false)User user){
        int i = 0;
        try {
            i = userDao.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/delete")
    public int delete(@RequestBody Map<String, String> map){

        int i = 0;
        try {
            i = userDao.deleteUser(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/edit")
    public int edit(@RequestBody User user){

        int i = 0;
        try {
            i = userDao.editUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/queryInfo")
    public User queryInfo(String id){
        User user = new User();
        try {
            user = userDao.queryInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/count")
    public int count(@RequestBody(required = false)Map<String, String> params){

        int i = 0;
        try {
            i = userDao.count(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}
