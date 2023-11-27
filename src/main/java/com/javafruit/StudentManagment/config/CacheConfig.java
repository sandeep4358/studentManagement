package com.javafruit.StudentManagment.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Important for generating  keys for cache..
 */

@Configuration
public class CacheConfig {
    @Bean("myCacheKeyGenerator")
    public KeyGenerator myKeyGenerator() {
        return new MyCacheKeyGenerator();
    }
}


class MyCacheKeyGenerator implements  KeyGenerator{
    @Override
    public Object generate(Object target, Method method, Object... params) {
        if (params.length > 2) {
            // only the first two, please! this was useful in my case.
            // omit or use like this, if (not) needed
            params = Arrays.copyOf(params, 2);
        }
        var key = String.format("mykey_%s", StringUtils.arrayToDelimitedString(params, "_"));
        return key;
    }


}