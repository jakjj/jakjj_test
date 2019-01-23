package org.wz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class ComputerController {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    DiscoveryClient client;

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Integer add(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b){
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a+b;
        logger.info("/add host:"+instance.getHost()+"service_Id:"+instance.getServiceId()+"result:"+r);
        return r;
    }
}
