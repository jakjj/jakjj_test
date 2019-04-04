package org.wz.bussiness.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wz.bussiness.filter.RequestCachingFilter;

/**
 * @project: userMaster
 * @package: org.wz.config.config
 * @author: Administrator
 * @crDate: 2019/3/25 14:43
 * @editor: IntelliJ IDEA
 * @role:
 **/
@Configuration
public class filterConfig {
    @Bean
    public RequestCachingFilter requestCachingFilter() {
        return new RequestCachingFilter();
    }

    @Bean
    public FilterRegistrationBean requestCachingFilterRegistration(
            RequestCachingFilter requestCachingFilter) {
        FilterRegistrationBean bean = new FilterRegistrationBean(requestCachingFilter);
        bean.setOrder(1);
        return bean;
    }
}
