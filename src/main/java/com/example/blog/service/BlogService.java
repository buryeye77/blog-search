package com.example.blog.service;

import com.example.blog.dto.blog.BlogResponse;
import com.example.blog.service.blog.BlogInterface;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogService {
    private Map<String, BlogInterface> serviceMap = new HashMap<>();

    public BlogService(List<BlogInterface> blogInterfaceList) {
        blogInterfaceList.stream().forEach(blogInterface -> serviceMap.put(blogInterface.getName(), blogInterface));
    }

    public BlogResponse getBlogs(String query, String sort, int page, int size, String type) {
        return serviceMap.get(type).getBlogs(query, sort, page, size);
    }
}
