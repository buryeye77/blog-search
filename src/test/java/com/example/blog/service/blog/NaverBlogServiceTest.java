package com.example.blog.service.blog;

import com.example.blog.dto.blog.BlogResponse;
import com.example.blog.dto.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class NaverBlogServiceTest {

    @Autowired
    NaverBlogService naverBlogService;

    @Test
    public void TestGetBlogs() {
        assertThrows(CustomException.class, () -> {
            BlogResponse blogResponse = naverBlogService.getBlogs("대나무", "accuracy", 11, 100);
        });
    }

    @Test
    public void TestValidateRequestParams() {
        assertThrows(CustomException.class, () -> {
            naverBlogService.validateRequestParams(101, 10);
        });
        assertThrows(CustomException.class, () -> {
            naverBlogService.validateRequestParams(50, 105);
        });
    }
}
