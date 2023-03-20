package com.jojoldu.book.springbootwebservice.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void mainPage() {
//        String body = restTemplate.getForObject("/", String.class);

//        Assertions.assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
    }
}