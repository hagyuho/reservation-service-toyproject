package com.hadoyaji.movereservation.springboot.web.dto;

import com.hadoyaji.movereservation.springboot.domain.reservations.Reservations;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationListResponseDto {

    private long reservationId;

    private LocalDateTime moveDate;

    private String reservationYn;

    private String cancelReason;

    private String dong;

    private String ho;

    private String hpNumber;

    private LocalDateTime reservationDate;

    public ReservationListResponseDto(Reservations entity){
        this.reservationId = entity.getId();
        this.moveDate = entity.getMoveDate();
        this.reservationYn = entity.getReservationYn();
        this.cancelReason = entity.getCancelReason();
        this.dong = entity.getAparts().getDong();
        this.ho = entity.getAparts().getHo();
        this.hpNumber = entity.getHpNumber();
        this.reservationDate = entity.getModifiedDate();
    }
}
