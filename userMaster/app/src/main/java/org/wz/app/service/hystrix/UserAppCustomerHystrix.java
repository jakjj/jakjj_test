package org.wz.app.service.hystrix;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.wz.app.service.UserAppCustomer;
import org.wz.config.bean.User;

import java.util.List;
import java.util.Map;

/**
 * @project: userMaster
 * @package: org.wz.app.service.hystrix
 * @author: Administrator
 * @crDate: 2019/3/23 14:50
 * @editor: IntelliJ IDEA
 * @role:
 **/
@Component
public class UserAppCustomerHystrix implements UserAppCustomer {
    @Override
    public List<User> queryList(Map<String, String> params) {
        return null;
    }

    @Override
    public int addUser(User user) {
        return -99;
    }

    @Override
    public int deleteUser(Map<String,String> map) {
        return -99;
    }

    @Override
    public User queryInfo(String id) {
        return null;
    }

    @Override
    public int editUser(User user) {
        return -99;
    }

    @Override
    public int count(Map<String, String> map) {
        return -99;
    }
}
