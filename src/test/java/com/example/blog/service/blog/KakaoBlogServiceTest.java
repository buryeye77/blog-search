package com.example.blog.service.blog;

import com.example.blog.dto.blog.BlogResponse;
import com.example.blog.dto.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class KakaoBlogServiceTest {

    @Autowired
    KakaoBlogService kakaoBlogService;

    @Test
    public void TestGetBlogs() {
        assertThrows(CustomException.class, () -> {
            BlogResponse blogResponse = kakaoBlogService.getBlogs("대나무", "accuracy", 51, 50);
        });
    }

    @Test
    public void TestValidateRequestParams() {
        assertThrows(CustomException.class, () -> {
            kakaoBlogService.validateRequestParams(100, 50);
        });
        assertThrows(CustomException.class, () -> {
            kakaoBlogService.validateRequestParams(50, 100);
        });
    }
}
