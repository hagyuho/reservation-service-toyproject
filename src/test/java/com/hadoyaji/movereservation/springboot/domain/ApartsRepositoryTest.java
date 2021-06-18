package com.hadoyaji.movereservation.springboot.domain;

import com.hadoyaji.movereservation.springboot.domain.aparts.Aparts;
import com.hadoyaji.movereservation.springboot.domain.aparts.ApartsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApartsRepositoryTest {

    @Autowired
    ApartsRepository apartsRepository;

    @After
    public void cleanup(){apartsRepository.deleteAll();}

    @Test
    public void 아파트정보_가져오기(){
        //given
        String dong = "101";
        String ho = "201";

        apartsRepository.save(Aparts.builder()
                .dong(dong)
                .ho(ho)
                .build());
        //when
        Aparts aparts = apartsRepository.findByDongAndHo(dong,ho);

        //then
        assertThat(aparts.getId()).isEqualTo(Long.parseLong(dong.concat(ho)));
        assertThat(aparts.getHo()).isEqualTo(ho);
        assertThat(aparts.getDong()).isEqualTo(dong);

    }
}
