package com.hadoyaji.movereservation.springboot.web.dto;

import com.hadoyaji.movereservation.springboot.domain.aparts.Aparts;
import com.hadoyaji.movereservation.springboot.domain.reservations.Reservations;
import com.hadoyaji.movereservation.springboot.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationListResponseDto {

    private long reservationId;

    private LocalDateTime moveDate;

    private String reservationYn;

    private String cancelReason;

    private User user;

    private Aparts aparts;

    private LocalDateTime reservationDate;

    public ReservationListResponseDto(Reservations entity){
        this.reservationId = entity.getId();
        this.moveDate = entity.getMoveDate();
        this.reservationYn = entity.getReservationYn();
        this.cancelReason = entity.getCancelReason();
        this.user = entity.getUser();
        this.aparts = entity.getAparts();
        this.reservationDate = entity.getModifiedDate();
    }
}
