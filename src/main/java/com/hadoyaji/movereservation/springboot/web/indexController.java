package com.hadoyaji.movereservation.springboot.web;

import com.hadoyaji.movereservation.springboot.service.reservations.ReservationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class indexController {
    private final ReservationsService reservationsService;

    /**
     * @author      : hagyuho
     * @date        : 2021. 06. 16
     * @method      : findAll
     * @description : 전체조회 / index화면
     */
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("reservations", reservationsService.findAllDesc());
        return "index";
    }



}
