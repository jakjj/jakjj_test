package org.wz.web;

import freemarker.log.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.wz.service.ConsumerService;

@RestController
@Api("消费者，add方法")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    @ApiOperation(value="add，实现10+20的计算")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Integer add(){
        return consumerService.add(10,20);
    }






   /* @Autowired
    RestTemplate restTemplate;*/

   /* @RequestMapping(value="/add",method= RequestMethod.GET)
    public String add(){
        return restTemplate.getForEntity("http://FAMILY-BUSINESS/add?a=10&b=20",String.class).getBody();
    }*/

}
