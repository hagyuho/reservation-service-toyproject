package com.hadoyaji.movereservation.springboot.web;

import com.hadoyaji.movereservation.springboot.service.reservations.ReservationsService;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class indexController {
    private final ReservationsService reservationsService;

    /**
     * @author      : hagyuho
     * @date        : 2021. 06. 16
     * @method      : welcome
     * @description : 웰컴화면/로그인
     */
    @GetMapping("/welcome")
    public String welcome(Model model){
        return "movereservation-login";
    }

    /**
     * @author      : hagyuho
     * @date        : 2021. 06. 16
     * @method      : index
     * @description : 전체조회 / index화면
     */
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("reservations", reservationsService.findAllDesc());

        return "index";
    }

    /**
     * @author      : hagyuho
     * @date        : 2021. 06. 16
     * @method      : movereservationSave
     * @description : 저장화면으로이동
    */
    @GetMapping("/movereservation/save")
    public String movereservationSave(){
        return  "movereservation-save";
    }

    /**
     * @author      : hagyuho
     * @date        : 2021. 06. 16
     * @method      : movereservationCancel
     * @description : 취소화면으로 이동
     */
    @GetMapping("/movereservation/cancel/{reservationId}")
    public String movereservationCancel(@PathVariable Long reservationId, Model model){
        ReservationResponseDto dto = reservationsService.findById(reservationId);
        model.addAttribute("reservations",dto);
        return "movereservation-cancel";
    }

}
