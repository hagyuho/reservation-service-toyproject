package com.hadoyaji.movereservation.springboot.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class indexControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void 메인화면_로딩(){
        //when
        String body = this.testRestTemplate.getForObject("/",String.class);

        //then
        assertThat(body).contains("이사 예약 서비스");
    }
}
