package org.wz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsurmerController {

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping(value="/add",method= RequestMethod.GET)
    public String add(){
        return restTemplate.getForEntity("http://FAMILY-BUSINESS/add?a=10&b=20",String.class).getBody();
    }

}
