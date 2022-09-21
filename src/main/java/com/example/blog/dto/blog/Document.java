package com.example.blog.dto.blog;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Document {

    @JsonProperty("title")
    private String title;

    @JsonProperty("contents")
    private String contents;

    @JsonProperty("blogname")
    private String blogName;

    @JsonProperty("postdate")
    private String postDate;

    @JsonProperty("link")
    private String link;
}
