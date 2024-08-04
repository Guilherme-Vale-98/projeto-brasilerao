package com.gm.brasilerao.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                String token = System.getenv("TOKEN");
                if (token != null && !token.isEmpty()) {
                    template.header("X-Auth-Token", token);
                }
            }
        };
    }
}