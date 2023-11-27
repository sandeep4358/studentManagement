package com.javafruit.StudentManagment.config;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

/**
 * This class is first develop for implementation of the ET tag(entity tag) for idempotent request
 * Date Create : 18/10/2023
 * Reference :- SPRINGBOOT10053-55 in ExcelSheet google
 */
@Configuration
public class CustomConfigImpl {
    @Bean
    public Filter getShallowEtagHeaderFilter(){

        return  new ShallowEtagHeaderFilter();
    }
}
