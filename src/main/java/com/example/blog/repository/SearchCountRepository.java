package com.example.blog.repository;

import com.example.blog.entity.SearchCountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchCountRepository extends JpaRepository<SearchCountEntity, Long> {
    List<SearchCountEntity> findTop10ByOrderByCountDesc();
    SearchCountEntity findOneByKeyword(String keyword);
}
