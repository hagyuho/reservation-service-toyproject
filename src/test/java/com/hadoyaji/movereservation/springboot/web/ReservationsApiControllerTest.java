package com.hadoyaji.movereservation.springboot.web;

import com.hadoyaji.movereservation.springboot.service.reservations.ReservationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReservationsApiControllerTest {

    private final ReservationsService reservationsService;



}
