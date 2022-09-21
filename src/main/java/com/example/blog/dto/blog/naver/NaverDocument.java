package com.example.blog.dto.blog.naver;

import com.example.blog.dto.blog.Document;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NaverDocument {
    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("bloggername")
    private String bloggerName;

    @JsonProperty("bloggerlink")
    private String bloggerLink;

    @JsonProperty("postdate")
    private String postDate;

    @JsonProperty("link")
    private String link;

    public Document buildDocument() {
        return Document.builder()
                .title(title)
                .contents(description)
                .blogName(bloggerName)
                .link(link)
                .postDate(postDate)
                .build();
    }
}
