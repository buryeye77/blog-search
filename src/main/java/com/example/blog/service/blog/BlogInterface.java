package com.example.blog.service.blog;

import com.example.blog.dto.blog.BlogResponse;

public interface BlogInterface {
    String getName();
    BlogResponse getBlogs(String query, String sort, int page, int size);
}
