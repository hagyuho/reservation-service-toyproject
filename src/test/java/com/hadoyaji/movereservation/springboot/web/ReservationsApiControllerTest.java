package com.hadoyaji.movereservation.springboot.web;

import com.hadoyaji.movereservation.springboot.domain.reservations.Reservations;
import com.hadoyaji.movereservation.springboot.domain.reservations.ReservationsRepository;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationCancelRequestDto;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReservationsRepository reservationsRepository;


//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mvc;
//
//    @Before
//    public void setup() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }

    @After
    public void tearDown() throws Exception {
            reservationsRepository.deleteAll();
    }

    @Test
    public void Reservations_예약처리() throws Exception{
        //given
        LocalDateTime moveDate = LocalDateTime.now();
        String reservationYn = "Y";
        ReservationSaveRequestDto requestDto = ReservationSaveRequestDto.builder()
                .moveDate(moveDate)
                .reservationYn(reservationYn)
                .build();

        String url = "http://localhost:"+port+"/api/v1/reservations";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);
//        mvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Reservations> list = reservationsRepository.findAll();
        assertThat(list.get(0).getReservationYn()).isEqualTo(reservationYn);
    }

    @Test
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
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity ,Long.class);


        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Reservations> list = reservationsRepository.findAll();
        assertThat(list.get(0).getReservationYn()).isEqualTo("N");
        assertThat(list.get(0).getCancelReason()).isEqualTo(cancelReason);

    }

}
