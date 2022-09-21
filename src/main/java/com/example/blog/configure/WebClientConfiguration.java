package com.example.blog.configure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Value("${openapi.kakao.url}")
    private String kakaoUrl;

    @Value("${openapi.kakao.authorization}")
    private String kakaoAuth;

    @Value("${openapi.naver.url}")
    private String naverUrl;

    @Value("${openapi.naver.client-id}")
    private String naverClientId;

    @Value("${openapi.naver.client-secret}")
    private String naverClientSecret;

    @Qualifier("kakao")
    @Bean
    public WebClient webClientKakaoApi() {
        return WebClient.builder()
                .baseUrl(kakaoUrl)
                .defaultHeader("Authorization", "KakaoAK " + kakaoAuth)
                .build();
    }

    @Qualifier("naver")
    @Bean
    public WebClient webClientNaverApi() {
        return WebClient.builder()
                .baseUrl(naverUrl)
                .defaultHeader("X-Naver-Client-Id", naverClientId)
                .defaultHeader("X-Naver-Client-Secret", naverClientSecret)
                .build();
    }
}
