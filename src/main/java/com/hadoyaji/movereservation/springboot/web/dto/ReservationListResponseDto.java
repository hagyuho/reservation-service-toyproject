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

    private Long userId;

    private Long apartId;

    private LocalDateTime reservationDate;

    public ReservationListResponseDto(Reservations entity){
        this.reservationId = entity.getReservationId();
        this.moveDate = entity.getMoveDate();
        this.reservationYn = entity.getReservationYn();
        this.cancelReason = entity.getCancelReason();
//        this.userId = entity.getUserId();
//        this.apartId = entity.getApartId();
        this.reservationDate = entity.getModifiedDate();
    }
}
