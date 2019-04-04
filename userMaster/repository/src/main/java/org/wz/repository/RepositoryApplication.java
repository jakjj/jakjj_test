package org.wz.repository;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @project: userMaster
 * @package: org.wz
 * @author: Administrator
 * @crDate: 2019/3/21 12:01
 * @editor: IntelliJ IDEA
 * @role:
 **/
@MapperScan(basePackages = "org.wz.repository.dao")
@SpringBootApplication
@EnableEurekaClient
public class RepositoryApplication {
    public static void main(String[] args){
        SpringApplication.run(RepositoryApplication.class,args);
    }
}
