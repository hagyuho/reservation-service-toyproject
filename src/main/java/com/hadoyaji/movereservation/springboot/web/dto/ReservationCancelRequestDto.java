package com.hadoyaji.movereservation.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReservationCancelRequestDto {
    private String cancelReason;

    @Builder
    public ReservationCancelRequestDto(String cancelReason){
        this.cancelReason = cancelReason;
    }
}
