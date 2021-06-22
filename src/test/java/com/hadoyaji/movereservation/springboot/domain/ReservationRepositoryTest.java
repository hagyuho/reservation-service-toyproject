package com.hadoyaji.movereservation.springboot.domain;


import com.hadoyaji.movereservation.springboot.domain.reservations.Reservations;
import com.hadoyaji.movereservation.springboot.domain.reservations.ReservationsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationRepositoryTest {

    @Autowired
    ReservationsRepository reservationsRepository;

    @After
    public void cleanup(){reservationsRepository.deleteAll();}

    @Test
    public void 예약정보_가져오기(){
        //given

        LocalDateTime now = LocalDateTime.now();

        reservationsRepository.save(Reservations.builder()
                .reservationYn("Y")
                .moveDate(now)
                .build());

        //when
        List<Reservations> reservationsList = reservationsRepository.findAll();

        //then
        Reservations reservation = reservationsList.get(0);
        assertThat(reservation.getReservationYn()).isEqualTo("Y");
    }


    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2021,6,1,0,0,0);
        reservationsRepository.save(Reservations.builder()
                .reservationYn("Y")
                .moveDate(now)
                .build());

        //when
        List<Reservations> reservationsList = reservationsRepository.findAll();

        //then
        Reservations reservations = reservationsList.get(0);

        assertThat(reservations.getCreatedDate()).isAfter(now);
        assertThat(reservations.getModifiedDate()).isAfter(now);

    }
}
