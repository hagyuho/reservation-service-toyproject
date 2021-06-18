package com.hadoyaji.movereservation.springboot.web;

import com.hadoyaji.movereservation.springboot.domain.aparts.Aparts;
import com.hadoyaji.movereservation.springboot.domain.aparts.ApartsRepository;
import com.hadoyaji.movereservation.springboot.domain.user.User;
import com.hadoyaji.movereservation.springboot.domain.user.UserRepository;
import com.hadoyaji.movereservation.springboot.web.dto.UserSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApartsRepository apartsRepository;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
        apartsRepository.deleteAll();
    }

    @Test
    public void USER_등록처리() throws Exception{
        //given
        String hpNumber = "01071156415";
        String dong = "101";
        String ho = "201";

        apartsRepository.save(Aparts.builder()
                .dong(dong)
                .ho(ho)
                .build());

        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .hpNumber(hpNumber)
                .dong(dong)
                .ho(ho)
                .build();

        String url = "http://localhost:"+port+"/api/v1/user";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0l);

        List<User> user = userRepository.findAll();
        assertThat(user.get(0).getHpNumber()).isEqualTo(hpNumber);
        assertThat(user.get(0).getAparts().getDong()).isEqualTo(dong);
        assertThat(user.get(0).getAparts().getHo()).isEqualTo(ho);


    }

}
