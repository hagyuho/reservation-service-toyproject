package com.hadoyaji.movereservation.springboot.web.dto;

import com.hadoyaji.movereservation.springboot.domain.reservations.Reservations;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationResponseDto {
    private long reservationId;

    private LocalDateTime moveDate;

    private String dong;

    private String ho;


    public ReservationResponseDto(Reservations entity){
        this.reservationId = entity.getId();
        this.moveDate = entity.getMoveDate();
        this.dong = entity.getAparts().getDong();
        this.ho = entity.getAparts().getHo();
    }


}
