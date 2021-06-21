package com.hadoyaji.movereservation.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadoyaji.movereservation.springboot.domain.aparts.Aparts;
import com.hadoyaji.movereservation.springboot.domain.aparts.ApartsRepository;
import com.hadoyaji.movereservation.springboot.domain.reservations.Reservations;
import com.hadoyaji.movereservation.springboot.domain.reservations.ReservationsRepository;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationCancelRequestDto;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationSaveRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReservationsRepository reservationsRepository;

    @Autowired
    private ApartsRepository apartsRepository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
            reservationsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void Reservations_예약처리() throws Exception{
        //given
        LocalDateTime moveDate = LocalDateTime.now();
        String reservationYn = "Y";
        String dong = "101";
        String ho = "201";

        apartsRepository.save(Aparts.builder()
                .dong(dong)
                .ho(ho)
                .build());

        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto.builder()
                .moveDate(moveDate)
                .reservationYn(reservationYn)
                .dong(dong)
                .ho(ho)
                .build();

        String url = "http://localhost:"+port+"/api/v1/reservations";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Reservations> list = reservationsRepository.findAll();
        assertThat(list.get(0).getAparts().getDong()).isEqualTo(dong);
        assertThat(list.get(0).getReservationYn()).isEqualTo(reservationYn);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Reservations_취소처리() throws Exception{
        //given
        LocalDateTime moveDate = LocalDateTime.now();
        Reservations saveReservation = reservationsRepository.save(Reservations.builder()
                .moveDate(moveDate)
                .reservationYn("Y")
                .build());
        Long reservationId = saveReservation.getId();

        String cancelReason = "test";
        ReservationCancelRequestDto requestDto = ReservationCancelRequestDto.builder()
                .cancelReason(cancelReason)
                .build();

        String url = "http://localhost:"+port+"/api/v1/reservations/"+reservationId;

        HttpEntity<ReservationCancelRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        //ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity ,Long.class);
        mvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then

        List<Reservations> list = reservationsRepository.findAll();
        assertThat(list.get(0).getReservationYn()).isEqualTo("N");
        assertThat(list.get(0).getCancelReason()).isEqualTo(cancelReason);

    }

}
