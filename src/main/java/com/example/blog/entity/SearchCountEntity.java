package com.example.blog.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Table(name = "t_search_count", uniqueConstraints = {@UniqueConstraint(name = "KEYWORD_UNIQUE", columnNames = {"keyword"})})
@Entity
public class SearchCountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "count")
    private int count;
}
