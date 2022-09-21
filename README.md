# blog-search

1. API 명세 
A. 블로그 검색

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

B. 인기 검색어
GET /popular-keywords
Host: localhost:8080
  
 
