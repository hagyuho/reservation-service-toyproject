package com.hadoyaji.movereservation.springboot.web;

import com.hadoyaji.movereservation.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;


//    /**
//     * @author      : hagyuho
//     * @date        : 2021. 05. 21
//     * @method      : saveReservation
//     * @description : 유저 생성처리
//     */
//    @PostMapping("/api/v1/user")
//    public Long save(@RequestBody UserSaveRequestDto requestDto) throws Exception{
//        return userService.save(requestDto);
//    }
//
//    /**
//     * @author      : hagyuho
//     * @date        : 2021. 05. 21
//     * @method      : signin
//     * @description : 유저 로그인 처리
//     */
//    @PostMapping("/api/v1/user/signin")
//    public UserSigninResponseDto signin(@RequestBody UserSigninRequestDto requestDto) throws Exception{
//        return userService.signin(requestDto);
//    }
}
