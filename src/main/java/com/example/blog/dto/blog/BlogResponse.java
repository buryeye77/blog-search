package com.example.blog.dto.blog;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogResponse {

    @JsonProperty("search_count")
    private int searchCount;

    @JsonProperty("is_end")
    private boolean isEnd;

    @JsonProperty("documents")
    private List<Document> documentList;
}
