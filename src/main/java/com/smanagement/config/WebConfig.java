package com.smanagement.config;

import com.smanagement.filer.AuditorUserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean<AuditorUserFilter> filterRegistrationBean() {
        FilterRegistrationBean<AuditorUserFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AuditorUserFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }
}
