package com.example.blog.service.blog;

import com.example.blog.dto.blog.BlogResponse;
import com.example.blog.dto.blog.kakao.KakaoResponse;
import com.example.blog.dto.exception.CustomException;
import com.example.blog.dto.exception.ForwardException;
import com.example.blog.platform.exception.ErrorCode;
import io.netty.handler.timeout.ReadTimeoutException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

@Service
public class KakaoBlogService implements BlogInterface {

    private final WebClient webClient;
    private final PopularKeywordService popularKeywordService;
    private final NaverBlogService naverBlogService;

    public KakaoBlogService(@Qualifier("kakao") WebClient webClient,
                            PopularKeywordService popularKeywordService,
                            NaverBlogService naverBlogService) {
        this.webClient = webClient;
        this.popularKeywordService = popularKeywordService;
        this.naverBlogService = naverBlogService;
    }

    public BlogResponse getBlogs(String query, String sort, int page, int size) {
        validateRequestParams(page, size);

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/v2/search/blog")
                .queryParam("query", query)
                .queryParam("sort", sort)
                .queryParam("page", page)
                .queryParam("size", size)
                .build();

        BlogResponse blogResponse = null;
        try {
            blogResponse = webClient.mutate().build().get()
                    .uri(uriComponents.toString())
                    .retrieve()
                    .bodyToMono(KakaoResponse.class)
                    .timeout(Duration.ofSeconds(10))
                    .block()
                    .buildBlogResponse();
        } catch (ReadTimeoutException e) {
            // Kakao 검색 서비스가 장애인 경우 Naver 호출
            return naverBlogService.getBlogs(query, sort, page, size);
        } catch (WebClientResponseException e) {
            e.printStackTrace();
            throw new ForwardException(e.getStatusCode(), e.getMessage());
        }

        popularKeywordService.updateSearchCount(query);
        return blogResponse;
    }

    public String getName() {
        return "kakao";
    }

    void validateRequestParams(int page, int size) {
        if (size > 50) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "Size param must be less than or equal to 50");
        }
        if (page > 50) {
            throw new CustomException(ErrorCode.BAD_REQUEST, "Page param must be less than or equal to 50");
        }
    }
}