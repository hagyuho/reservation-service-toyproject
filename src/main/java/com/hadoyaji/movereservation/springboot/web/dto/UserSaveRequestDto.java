package com.hadoyaji.movereservation.springboot.web.dto;

import com.hadoyaji.movereservation.springboot.domain.aparts.Aparts;
import com.hadoyaji.movereservation.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {

    private String dong;
    private String ho;
    private String hpNumber;

    @Builder
    public UserSaveRequestDto(String dong, String ho, String hpNumber){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
