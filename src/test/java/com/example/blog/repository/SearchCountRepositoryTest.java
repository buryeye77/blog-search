package com.example.blog.repository;

import com.example.blog.entity.SearchCountEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SearchCountRepositoryTest {
    @Autowired
    private SearchCountRepository searchCountRepository;

    @Test
    @Transactional
    public void testFindAllOrderByCountDesc() {
        List<SearchCountEntity> searchCountEntityList = new ArrayList<>();

        SearchCountEntity searchCountEntity = new SearchCountEntity();
        searchCountEntity.setKeyword("first");
        searchCountEntity.setCount(40);
        searchCountRepository.save(searchCountEntity);
        searchCountEntityList.add(searchCountEntity);

        searchCountEntity = new SearchCountEntity();
        searchCountEntity.setKeyword("second");
        searchCountEntity.setCount(20);
        searchCountRepository.save(searchCountEntity);
        searchCountEntityList.add(searchCountEntity);

        searchCountEntity = new SearchCountEntity();
        searchCountEntity.setKeyword("third");
        searchCountEntity.setCount(30);
        searchCountRepository.save(searchCountEntity);
        searchCountEntityList.add(searchCountEntity);

        List<SearchCountEntity> outSearchCountEntityList = searchCountRepository.findTop10ByOrderByCountDesc();

        assertEquals(searchCountEntityList.get(0).getKeyword(), outSearchCountEntityList.get(0).getKeyword());
        assertEquals(searchCountEntityList.get(0).getCount(), outSearchCountEntityList.get(0).getCount());
        assertEquals(searchCountEntityList.get(2).getKeyword(), outSearchCountEntityList.get(1).getKeyword());
        assertEquals(searchCountEntityList.get(2).getCount(), outSearchCountEntityList.get(1).getCount());
        assertEquals(searchCountEntityList.get(1).getKeyword(), outSearchCountEntityList.get(2).getKeyword());
        assertEquals(searchCountEntityList.get(1).getCount(), outSearchCountEntityList.get(2).getCount());
    }

    @Test
    @Transactional
    public void testFindOneByKeyword() {

        SearchCountEntity inSearchCountEntity = new SearchCountEntity();
        inSearchCountEntity.setKeyword("first");
        inSearchCountEntity.setCount(40);
        searchCountRepository.save(inSearchCountEntity);

        SearchCountEntity outSearchCountEntity = searchCountRepository.findOneByKeyword("first");

        assertEquals(inSearchCountEntity.getKeyword(), outSearchCountEntity.getKeyword());
        assertEquals(inSearchCountEntity.getCount(), outSearchCountEntity.getCount());
    }
}
