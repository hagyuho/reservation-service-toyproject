package com.hadoyaji.movereservation.springboot.web.dto;

import com.hadoyaji.movereservation.springboot.domain.aparts.Aparts;
import com.hadoyaji.movereservation.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {

    private String dong;
    private String ho;
    private String hpNumber;

    @Builder
    public UserSaveRequestDto(String dong, String ho, String hpNumber){
        this.dong=dong;
        this.ho=ho;
        this.hpNumber=hpNumber;
    }

    public User toEntity(Aparts aparts){
        return User.builder()
                .hpNumber(hpNumber)
                .aparts(aparts)
                .build();
    }
}
