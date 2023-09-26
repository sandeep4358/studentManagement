package com.javafruit.StudentManagment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

/**
 * Created to remove the CORS error comming int he React Blog app.
 */
@Configuration
public class CustomSecurityConfig {

//    @Bean
//    public FilterRegistrationBean<CorsFilter> corsFilterRegistration(){
//        //step first create a Url base source
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        //Step 2 Cors Configuration
//
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type","Accept"));
//        configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//
//        source.registerCorsConfiguration("/**", configuration);
//
//
//
//        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(source);
//        return  bean;
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        //Step 2 Cors Configuration

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization","Content-Type","Accept"));
        //configuration.setAllowedOrigins(Arrays.asList("https://example.com","*"));
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter(CorsConfigurationSource corsConfigurationSource)
    {
        CorsFilter corsFilter = new CorsFilter((corsConfigurationSource));

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(corsFilter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
