package com.hadoyaji.movereservation.springboot.service.reservations;

import com.hadoyaji.movereservation.springboot.domain.reservations.Reservations;
import com.hadoyaji.movereservation.springboot.domain.reservations.ReservationsRepository;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationCancelRequestDto;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationListResponseDto;
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
    /**
     * @author      : hagyuho
     * @date        : 2021. 06. 16
     * @method      : findAll
     * @description : 전체조회
     */
    @Transactional(readOnly = true)
    public List<ReservationListResponseDto> findAllDesc(){
        return reservationsRepository.findAllDesc().stream().map(reservations -> new ReservationListResponseDto(reservations)).collect(Collectors.toList());
    }

    /**
     * @author      : hagyuho
     * @date        : 2021. 05. 21
     * @method      : findByHpNumber
     * @description : 전화번호로 예약건 조회
     */

    /**
     * @author      : hagyuho
     * @date        : 2021. 05. 21
     * @method      : getReservationList
     * @description : 조회조건별 예약목록조회
     *                1. 조회조건 존재하지 않을 시 전체조회
     *                2. 조회조건 존재 할 시 해당 조건으로 조회
     */

    /**
     * @author      : hagyuho
     * @date        : 2021. 05. 21
     * @method      : saveRsv
     * @description : 예약처리
     *                1. 예약 전 체크
     *                2. 예약처리
     */
    @Transactional
    public Long save(ReservationSaveRequestDto requestDto){
        return reservationsRepository.save(requestDto.toEntity()).getId();
    }


    /**
     * @author      : hagyuho
     * @date        : 2021. 05. 21
     * @method      : cancelRsv
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
     * @method      : rsvCheck
     * @description : 예약 전 체크(내부)
     *                1. 이사날짜 60일 이내 or 오늘 이전
     *                2. 동일 동 && 동일 날짜 && 예약여부 Y
     *                3. 동일연락처 && 예약여부 Y
     */

}
