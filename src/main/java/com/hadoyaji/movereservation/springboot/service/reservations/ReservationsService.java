package com.hadoyaji.movereservation.springboot.service.reservations;

import com.hadoyaji.movereservation.springboot.config.auth.dto.SessionUser;
import com.hadoyaji.movereservation.springboot.domain.aparts.Aparts;
import com.hadoyaji.movereservation.springboot.domain.aparts.ApartsRepository;
import com.hadoyaji.movereservation.springboot.domain.reservations.Reservations;
import com.hadoyaji.movereservation.springboot.domain.reservations.ReservationsRepository;
import com.hadoyaji.movereservation.springboot.domain.user.User;
import com.hadoyaji.movereservation.springboot.domain.user.UserRepository;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationCancelRequestDto;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationListResponseDto;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationResponseDto;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationsService {

    private final ReservationsRepository reservationsRepository;
    private final UserRepository userRepository;
    private final ApartsRepository apartsRepository;

    /**
     * @author      : hagyuho
     * @date        : 2021. 06. 16
     * @method      : findAll
     * @description : 전체조회
     */
    @Transactional(readOnly = true)
    public List<ReservationListResponseDto> findAllDesc(){
        return reservationsRepository.findAllDesc().stream().filter(p -> p.getReservationYn().equals("Y")).map(reservations -> new ReservationListResponseDto(reservations)).collect(Collectors.toList());
    }

    /**
     * @author      : hagyuho
     * @date        : 2021. 06. 16
     * @method      : findByUserId
     * @description : 내예약정보조회
     */
    public List<ReservationListResponseDto> findByUserId(String email) {
        //Long userId = userRepository.findIdByEmail(email).getId();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당정보가 없습니다."));
        return user.getReservations().stream().map(reservations -> new ReservationListResponseDto(reservations)).collect(Collectors.toList());
    }

    /**
     * @author      : hagyuho
     * @date        : 2021. 05. 21
     * @method      : findById
     * @description : 예약ID로 예약건 조회
     */
    @Transactional(readOnly = true)
    public ReservationResponseDto findById(Long id) {
        Reservations entity =  reservationsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 예약정보가 없습니다. reservationId="+id));
        return new ReservationResponseDto(entity);
    }




    /**
     * @author      : hagyuho
     * @date        : 2021. 05. 21
     * @method      : cancel
     * @description : 예약 취소 
     *                1. 예약여부 N 처리
     */
    @Transactional
    public long cancel(Long id, ReservationCancelRequestDto requestDto) {
        Reservations reservations = reservationsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 예약정보가 없습니다. reservationId="+id));
        reservations.cancelReservations(requestDto.getCancelReason());
        return id;
    }

    /**
     * @author      : hagyuho
     * @date        : 2021. 05. 21
     * @method      : save
     * @description : 예약처리
     *                1. 예약 전 체크
     *                2. 예약처리
     */
    @Transactional
    public Long save(ReservationSaveRequestDto requestDto, SessionUser sessionUser){
        Aparts aparts = apartsRepository.findByDongAndHo(requestDto.getDong(), requestDto.getHo());
        User user = userRepository.findIdByEmail(sessionUser.getEmail());
        return reservationsRepository.save(requestDto.toEntity(aparts,user)).getId();
    }

    /**
     * @author      : hagyuho
     * @date        : 2021. 05. 21
     * @method      : rsvCheck
     * @description : 예약 전 체크(내부)
     *                1. 이사날짜 60일 이내 or 오늘 이전
     *                2. 동일 동 && 동일 날짜 && 예약여부 Y
     *                3. 동일연락처 && 예약여부 Y
     */

}
