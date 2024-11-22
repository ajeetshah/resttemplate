package com.example.resttemplate.config;

import com.example.resttemplate.user.User;
import com.example.resttemplate.user.UserMixin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.setMixInAnnotation(User.class, UserMixin.class);
        objectMapper.registerModule(module);
        return objectMapper;
    }

}
