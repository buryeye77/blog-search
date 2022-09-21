package com.example.blog.dto.blog.kakao;

import com.example.blog.dto.blog.Document;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KakaoDocument {

    @JsonProperty("blogname")
    private String blogName;

    @JsonProperty("contents")
    private String contents;

    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    public Document buildDocument() {
        return Document.builder()
                .title(title)
                .contents(contents)
                .blogName(blogName)
                .link(url)
                .postDate(datetime)
                .build();
    }
}
