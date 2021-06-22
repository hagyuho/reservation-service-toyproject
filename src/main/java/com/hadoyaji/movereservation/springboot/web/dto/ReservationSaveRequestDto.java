package com.hadoyaji.movereservation.springboot.web.dto;

import com.hadoyaji.movereservation.springboot.domain.aparts.Aparts;
import com.hadoyaji.movereservation.springboot.domain.reservations.Reservations;
import com.hadoyaji.movereservation.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReservationSaveRequestDto {

    private String hpNumber;
    private String dong;
    private String ho;
    private LocalDateTime moveDate;
    private String reservationYn;

    @Builder
    public ReservationSaveRequestDto(String hpNumber, LocalDateTime moveDate, String reservationYn, String dong, String ho){
        this.hpNumber = hpNumber;
        this.dong = dong;
        this.ho = ho;
        this.moveDate = moveDate;
        this.reservationYn = reservationYn;
    }

    public Reservations toEntity(Aparts aparts, User user){
        return Reservations.builder()
                .hpNumber(hpNumber)
                .aparts(aparts)
                .user(user)
                .moveDate(moveDate)
                .reservationYn("Y")
                .build();
    }

}
