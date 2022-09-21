package com.example.blog.service.blog;

import com.example.blog.dto.blog.BlogResponse;
import com.example.blog.dto.blog.naver.NaverResponse;
import com.example.blog.dto.exception.CustomException;
import com.example.blog.dto.exception.ForwardException;
import com.example.blog.platform.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

@Service
public class NaverBlogService implements BlogInterface {

    private final WebClient webClient;
    private final PopularKeywordService popularKeywordService;

    public NaverBlogService(@Qualifier("naver") WebClient webClient, PopularKeywordService popularKeywordService) {
        this.webClient = webClient;
        this.popularKeywordService = popularKeywordService;
    }

    public BlogResponse getBlogs(String query, String sort, int page, int size) {
        validateRequestParams(page, size);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
                .path("/v1/search/blog.json")
                .queryParam("query", query)
                .queryParam("sort", getSort(sort))
                .queryParam("start", getStart(page, size))
                .queryParam("display", size);
        UriComponents uriComponents = uriComponentsBuilder.build();

        BlogResponse blogResponse = null;
        try {
            blogResponse = webClient.mutate().build().get()
                    .uri(uriComponents.toString())
                    .retrieve()
                    .bodyToMono(NaverResponse.class)
                    .timeout(Duration.ofSeconds(10))
                    .block()
                    .buildBlogResponse();
        } catch (WebClientResponseException e) {
            e.printStackTrace();
            throw new ForwardException(e.getStatusCode(), e.getMessage());
        }

        popularKeywordService.updateSearchCount(query);
        return blogResponse;
    }

    public String getName() {
        return "naver";
    }

    void validateRequestParams(int page, int size) {
        int start = getStart(page, size);
        if (size > 100) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "Size param must be less than or equal to 100");
        }
        if (start > 1000) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "(page - 1) * size + 1 must be less than or equal to 1000");
        }
    }

    private int getStart(int page, int size) {
        return (page - 1) * size + 1;
    }

    private String getSort(String sort) {
        if (sort.equals("recency")) {
            return "date";
        }
        return "sim";
    }
}
