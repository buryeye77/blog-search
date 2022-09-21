package com.example.blog.dto.blog.kakao;

import com.example.blog.dto.blog.BlogResponse;
import com.example.blog.dto.blog.Document;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KakaoResponse {

    @JsonProperty("meta")
    private KakaoMeta meta;

    @JsonProperty("documents")
    private List<KakaoDocument> documentList;

    public BlogResponse buildBlogResponse() {
        List<Document> documentList = this.documentList.stream().map(document -> {
            document.setDatetime(document.getDatetime().split("T")[0]);
            return document.buildDocument();
        }).collect(Collectors.toList());

        return BlogResponse.builder()
                .searchCount(this.meta.getPageableCount())
                .isEnd(this.meta.isEnd())
                .documentList(documentList)
                .build();
    }
}
