package com.example.blog.service.blog;

import com.example.blog.dto.blog.SearchCount;
import com.example.blog.entity.SearchCountEntity;
import com.example.blog.repository.SearchCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PopularKeywordService {
    private final SearchCountRepository searchCountRepository;

    public List<SearchCount> getPopularKeywords() {
        return searchCountRepository.findTop10ByOrderByCountDesc().stream().map(entity -> new SearchCount(entity)).collect(Collectors.toList());
    }

    @Transactional
    public void updateSearchCount(String keyword) {
        SearchCountEntity searchCountEntity = searchCountRepository.findOneByKeyword(keyword);
        if (searchCountEntity == null) {
            searchCountEntity = new SearchCountEntity();
            searchCountEntity.setKeyword(keyword);
            searchCountEntity.setCount(1);
            searchCountRepository.save(searchCountEntity);
            return;
        }
        searchCountEntity.setCount(searchCountEntity.getCount() + 1);
    }
}
