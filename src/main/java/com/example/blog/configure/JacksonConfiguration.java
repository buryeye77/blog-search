package com.example.blog.configure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
