package com.hadoyaji.movereservation.springboot.web.dto;

import com.hadoyaji.movereservation.springboot.domain.reservations.Reservations;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReservationSaveRequestDto {

    private LocalDateTime moveDate;
    private String reservationYn;

    @Builder
    public ReservationSaveRequestDto(LocalDateTime moveDate, String reservationYn){
        this.moveDate = moveDate;
        this.reservationYn = reservationYn;
    }

    public Reservations toEntity(){
        return Reservations.builder()
                .moveDate(moveDate)
                .reservationYn("Y")
                .build();
    }

}
