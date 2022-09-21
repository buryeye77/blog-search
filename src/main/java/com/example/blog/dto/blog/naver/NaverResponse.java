package com.example.blog.dto.blog.naver;

import com.example.blog.dto.blog.BlogResponse;
import com.example.blog.dto.blog.Document;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NaverResponse {

    @JsonProperty("lastBuildDate")
    private String lastBuildDate;

    @JsonProperty("total")
    private int total;

    @JsonProperty("start")
    private int start;

    @JsonProperty("display")
    private int display;

    @JsonProperty("items")
    private List<NaverDocument> documentList;

    public BlogResponse buildBlogResponse() {
        List<Document> documentList = this.documentList.stream().map(document -> {
            document.setPostDate(getFormattedDate(document.getPostDate()));
            return document.buildDocument();
        }).collect(Collectors.toList());

        boolean isEnd = getIsEnd();

        return BlogResponse.builder()
                .searchCount(this.total)
                .isEnd(isEnd)
                .documentList(documentList)
                .build();
    }

    String getFormattedDate(String date) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < date.length(); i++) {
            if (i == 4) sb.append("-");
            if (i == 6) sb.append("-");
            sb.append(date.charAt(i));
        }
        return sb.toString();
    }

    boolean getIsEnd() {
        return (this.start + this.display - 1) >= this.total ? true : false;
    }
}
