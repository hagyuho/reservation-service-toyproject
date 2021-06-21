package com.hadoyaji.movereservation.springboot.web;

import com.hadoyaji.movereservation.springboot.config.auth.LoginUser;
import com.hadoyaji.movereservation.springboot.config.auth.dto.SessionUser;
import com.hadoyaji.movereservation.springboot.service.reservations.ReservationsService;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class indexController {
    private final ReservationsService reservationsService;

    /**
     * @author      : hagyuho
     * @date        : 2021. 06. 16
     * @method      : signin
     * @description : 로그인페이지로 이동
     */
    @GetMapping("/movereservation/signin")
    public String signin(Model model){
        return "movereservation-signin";
    }

    /**
     * @author      : hagyuho
     * @date        : 2021. 06. 16
     * @method      : signout
     * @description : 로그아웃
     */
    @GetMapping("/movereservation/signout")
    public String signout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }



    /**
     * @author      : hagyuho
     * @date        : 2021. 06. 16
     * @method      : signup
     * @description : 회원가입페이지로 이동
     */
    @GetMapping("/movereservation/signup")
    public String signup(Model model){
        return "movereservation-signup";
    }

    /**
     * @author      : hagyuho
     * @date        : 2021. 06. 16
     * @method      : index
     * @description : 전체조회 / index화면
     */
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("reservations", reservationsService.findAllDesc());
        if (user != null){
            model.addAttribute("userName",user.getName());
        }
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
