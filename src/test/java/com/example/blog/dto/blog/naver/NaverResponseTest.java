package com.example.blog.dto.blog.naver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NaverResponseTest {

    @Test
    @DisplayName("날짜 포멧 변경 확인")
    public void testGetFormattedDate() {
        NaverResponse naverResponse = new NaverResponse();
        String input = "20220917";
        assertEquals("2022-09-17", naverResponse.getFormattedDate(input));
    }

    @Test
    @DisplayName("마지막 페이지인 지 확인")
    public void testGetIsEnd() {
        NaverResponse naverResponse = new NaverResponse();

        naverResponse.setStart(1);
        naverResponse.setDisplay(10);
        naverResponse.setTotal(100);
        assertEquals(false, naverResponse.getIsEnd());

        naverResponse.setStart(90);
        naverResponse.setDisplay(10);
        naverResponse.setTotal(100);
        assertEquals(false, naverResponse.getIsEnd());

        naverResponse.setStart(91);
        naverResponse.setDisplay(10);
        naverResponse.setTotal(100);
        assertEquals(true, naverResponse.getIsEnd());

        naverResponse.setStart(100);
        naverResponse.setDisplay(10);
        naverResponse.setTotal(100);
        assertEquals(true, naverResponse.getIsEnd());
    }
}
