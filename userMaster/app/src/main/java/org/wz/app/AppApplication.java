package org.wz.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @project: userMaster
 * @package: org.wz.app
 * @author: Administrator
 * @crDate: 2019/3/21 11:42
 * @editor: IntelliJ IDEA
 * @role:
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AppApplication {
    public static void main(String[] args){
        SpringApplication.run(AppApplication.class,args);
    }


}
