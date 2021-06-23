package com.hadoyaji.movereservation.springboot.service.reservations;

import com.hadoyaji.movereservation.springboot.domain.reservations.Reservations;
import com.hadoyaji.movereservation.springboot.domain.reservations.ReservationsRepository;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationListResponseDto;
import com.hadoyaji.movereservation.springboot.web.dto.ReservationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationCheckService {
    private final ReservationsRepository reservationsRepository;

    /**
     * @author : hagyuho
     * @date : 2021. 05. 21
     * @method : reservationCheck
     * @description : 예약 전 체크(내부)
     * 1. 이사날짜 60일 이내 or 오늘 이전
     * 2. 동일 동 && 동일 날짜 && 예약여부 Y
     * 3. 동일연락처 && 예약여부 Y
     */
    protected boolean reservationCheck(ReservationSaveRequestDto requestDto) {
            boolean reservationCheck = false;
            if (dayCheck(requestDto) && overlapReservationCheck(requestDto)) {
                reservationCheck = true;
            }
            return reservationCheck;
    }



    //1. 이사날짜 60일 이내 or 오늘 이전
    private boolean dayCheck(ReservationSaveRequestDto requestDto) {
        boolean dayCheck = true;
        //1. 이사날짜 60일 이내 or 오늘 이전
        if (requestDto.getMoveDate().minusDays(60).compareTo(LocalDateTime.now()) > 0) {
            throw new IllegalArgumentException("오늘로부터 60일 이내까지만 예약가능합니다.");
        } else if (requestDto.getMoveDate().compareTo(LocalDateTime.now()) < 0) {
            throw new IllegalArgumentException("오늘 이후 일자부터 예약가능합니다.");
        }
        return dayCheck;

    }

     // 2. 동일 동 && 동일 날짜 && 예약여부 Y
     // 3. 동일연락처 && 예약여부 Y
    private boolean overlapReservationCheck(ReservationSaveRequestDto requestDto) {
        boolean overlapReservationCheck = true;
        List<Reservations> entities = reservationsRepository.findAllByReservationYnAndMoveDateGreaterThanEqual("Y", LocalDateTime.now());
        List<ReservationListResponseDto> dtos = entities.stream().map(reservations -> new ReservationListResponseDto(reservations)).collect(Collectors.toList());
        List<ReservationListResponseDto> checkDtoForDong = dtos.stream()
                .filter(p -> p.getDong().equals(requestDto.getDong()))
                .filter(p -> p.getMoveDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        .equals(requestDto.getMoveDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                .collect(Collectors.toList());
        if (checkDtoForDong.size() > 0) {
            throw new IllegalArgumentException("이사 희망 일자에 이미 예약 처리 되어있습니다.");
        }

        List<ReservationListResponseDto> checkDtoforHpNumber = dtos.stream().filter(p -> p.getHpNumber().equals(requestDto.getHpNumber())).collect(Collectors.toList());
        if (checkDtoforHpNumber.size() > 0) {
            throw new IllegalArgumentException("동일 휴대폰 번호로 1회만 예약 가능합니다.");
        }

        return overlapReservationCheck;
    }

}
