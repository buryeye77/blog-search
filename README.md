# blog-search

1. API 명세
 
1-1. 블로그 검색

기본 정보
GET /blogs
Host: localhost:8080

Request

|Name|Type|Description|Required|
|-----|-----|--------------------|-----|
|query|String|검색을 원하는 질의어    |O|
|sort|String|결과문서 정렬 방식, accuracy(정확도순) 또는 recency (최신순), 기본값 accuracy|X|
|page|Integer|결과 페이지 번호, 1 ~ 50 사이의 값, 기본값 1|X|
|size|Integer|한 페이지에 보여질 문서의 수, 1 ~ 50 사이의 값, 기본값 10|X|
|type|String|검색 소스, 현재는 kakao 외에 지원 안 됨, 기본값 kakao|X|

Response

|Name|Type|Description|
|-----|-----|--------------------|
|search_count|Integer|갬색된 문서 수    |
|is_end|Boolean|현재 페이지가 마지막 페이지인 지 여부|
|documents|Document|검색한 블로그 정보|

Document
|Name|Type|Description|
|-----|-----|--------------------|
|search_count|Integer|갬색된 문서 수    |
|is_end|Boolean|현재 페이지가 마지막 페이지인 지 여부|
|documents|Document|검색한 블로그 정보|
|search_count|Integer|갬색된 문서 수    |
|is_end|Boolean|현재 페이지가 마지막 페이지인 지 여부|
|documents|Document|검색한 블로그 정보|


1-2. 인기 검색어
기본 정보
GET /popular-keywords
Host: localhost:8080
  
 
