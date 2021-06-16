package com.hadoyaji.movereservation.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReservationCancelRequestDto {
    private String cancelReason;

    @Builder
    public ReservationCancelRequestDto(String cancelReason){
        this.cancelReason = cancelReason;
    }
}
