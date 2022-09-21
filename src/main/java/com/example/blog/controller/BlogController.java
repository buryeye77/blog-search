package com.example.blog.controller;

import com.example.blog.dto.blog.BlogResponse;
import com.example.blog.dto.blog.SearchCount;
import com.example.blog.service.BlogService;
import com.example.blog.service.blog.PopularKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final PopularKeywordService popularKeywordService;

    @GetMapping("/blogs")
    public ResponseEntity<BlogResponse> getBlogs(@RequestParam(required = true) String query,
                                                 @RequestParam(required = false, defaultValue = "accuracy") String sort,
                                                 @RequestParam(required = false, defaultValue = "1") int page,
                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                 @RequestParam(required = false, defaultValue = "kakao") String type) {
        // API 추가될 경우 리스트에 추가
        type = List.of("kakao").contains(type) ? type : "kakao";
        return new ResponseEntity<>(blogService.getBlogs(query, sort, page, size, type), HttpStatus.OK);
    }

    @GetMapping("/popular-keywords")
    public ResponseEntity<List<SearchCount>> getPopularKeywords() {
        return new ResponseEntity<>(popularKeywordService.getPopularKeywords(), HttpStatus.OK);
    }
}
