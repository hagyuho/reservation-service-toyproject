package com.hadoyaji.movereservation.springboot.web;

import com.hadoyaji.movereservation.springboot.service.user.UserService;
import com.hadoyaji.movereservation.springboot.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;


    /**
     * @author      : hagyuho
     * @date        : 2021. 05. 21
     * @method      : saveReservation
     * @description : 유저 생성처리
     */
    @PostMapping("/api/v1/user")
    public Long save(@RequestBody UserSaveRequestDto requestDto){
        return userService.save(requestDto);
    }
}
