package com.amm.loginserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**").allowedOrigins("*", "http://localhost:4200/newuser").allowedMethods("*").maxAge(8400)
                .allowedHeaders("*", "http://localhost:4200/newuser")
                .exposedHeaders("Authorization").allowCredentials(true);
    }

}
