package com.example.blog.dto.blog;

import com.example.blog.entity.SearchCountEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchCount {

    @JsonProperty("keyword")
    private String keyword;

    @JsonProperty("count")
    private int count;

    public SearchCount(SearchCountEntity entity) {
        this.keyword = entity.getKeyword();
        this.count = entity.getCount();
    }
}
