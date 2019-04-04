package org.wz.bussiness.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wz.config.bean.User;
import org.wz.config.utils.Response;

import java.util.Map;

/**
 * @project: userMaster
 * @package: org.wz.bussiness.service
 * @author: Administrator
 * @crDate: 2019/3/21 15:56
 * @editor: IntelliJ IDEA
 * @role:
 **/
@FeignClient(value = "family-app")
public interface UserBusCustomer {
    @RequestMapping(method = RequestMethod.GET,value = "/appList")
    Response queryList(Map<String, String> params);

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    Response addUser(User user);

    @RequestMapping(value = "/delete",method = RequestMethod.PUT)
    Response deleteUser(Map<String, String> map);

    @RequestMapping(value = "/queryInfo",method = RequestMethod.GET)
    Response queryInfo(String id);

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    Response editUser(User user);
}
