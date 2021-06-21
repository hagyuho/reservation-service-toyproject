package com.hadoyaji.movereservation.springboot.web.dto;

import com.hadoyaji.movereservation.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSigninResponseDto {

    private String hpNumber;
    private String dong;
    private String ho;


    @Builder
    public UserSigninResponseDto(User entity){
        this.hpNumber=entity.getHpNumber();
        this.dong=entity.getAparts().getDong();
        this.ho=entity.getAparts().getHo();
    }

}
