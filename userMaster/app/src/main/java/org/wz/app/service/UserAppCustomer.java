package org.wz.app.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wz.app.service.hystrix.UserAppCustomerHystrix;
import org.wz.config.bean.User;

import java.util.List;
import java.util.Map;

/**
 * @project: userManager
 * @package: com.wonders.service
 * @author: Administrator
 * @crDate: 2019/3/19 14:58
 * @editor: IntelliJ IDEA
 * @role:
 **/
@FeignClient(value = "family-repository",fallback = UserAppCustomerHystrix.class)
public interface UserAppCustomer {

    @RequestMapping(value = "/repList",method = RequestMethod.GET)
    List<User> queryList(Map<String, String> params);

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    int addUser(User user);

    @RequestMapping(value = "/delete",method = RequestMethod.PUT)
    int deleteUser(Map<String,String> map);

    @RequestMapping(value = "/queryInfo",method = RequestMethod.GET)
    User queryInfo(String id);

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    int editUser(User user);

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    int count(Map<String, String> params);
}
