package com.hadoyaji.movereservation.springboot.web.dto;

import com.hadoyaji.movereservation.springboot.domain.aparts.Aparts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApartsSaveRequestDto {

    private String dong;
    private String ho;

    @Builder
    public ApartsSaveRequestDto(String dong, String ho){
        this.dong = dong;
        this.ho = ho;
    }

    public Aparts toEntity(){
        return Aparts.builder()
                .dong(dong)
                .ho(ho)
                .build();
    }

}
