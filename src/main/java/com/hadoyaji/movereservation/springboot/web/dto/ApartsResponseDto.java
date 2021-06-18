package com.hadoyaji.movereservation.springboot.web.dto;

import com.hadoyaji.movereservation.springboot.domain.aparts.Aparts;
import lombok.Getter;

@Getter
public class ApartsResponseDto {

    private long id;
    private String dong;
    private String ho;


    public ApartsResponseDto(Aparts entity){
        this.id = entity.getId();
        this.dong = entity.getDong();
        this.ho = entity.getHo();
    }
}
