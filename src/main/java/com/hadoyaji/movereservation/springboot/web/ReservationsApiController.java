package com.hadoyaji.movereservation.springboot.web;

import com.hadoyaji.movereservation.springboot.config.auth.LoginUser;
import com.hadoyaji.movereservation.springboot.config.auth.dto.SessionUser;
import com.hadoyaji.movereservation.springboot.service.reservations.ReservationsService;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationCancelRequestDto;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReservationsApiController {

    private final ReservationsService reservationsService;


    /**
     * @author      : hagyuho
     * @date        : 2021. 05. 21
     * @method      : saveReservation
     * @description : 예약처리
     */
    @PostMapping("/api/v1/reservations")
    public Long save(@RequestBody ReservationSaveRequestDto requestDto,@LoginUser SessionUser user){
        return reservationsService.save(requestDto,user);
    }

    /**
     * @author      : hagyuho
     * @date        : 2021. 05. 21
     * @method      : canselReservation
     * @description : 예약취소
     */
    @DeleteMapping("/api/v1/reservations/{id}")
    public long cancel(@PathVariable Long id, @RequestBody ReservationCancelRequestDto requestDto){
        return reservationsService.cancel(id, requestDto);

    }
}
