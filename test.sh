#!/bin/bash

WORDS="사과 영어 필요 날파리 김구라 책 과학 공학 수학 아이돌 자바"
for word in $WORDS;
do
    curl -G "http://localhost:8080/blogs" --data-urlencode "query=${word}" --data-urlencode "sort=accuracy" --data-urlencode "page=20" --data-urlencode "size=20" | jq .
done

WORDS="사과 필요 김구라 과학 수학 자바"
for word in $WORDS;
do
    curl -G "http://localhost:8080/blogs" --data-urlencode "query=${word}" --data-urlencode "sort=accuracy" --data-urlencode "page=20" --data-urlencode "size=20" --data-urlencode "type=naver" | jq .
done

