package com.hadoyaji.movereservation.springboot.service.user;

import com.hadoyaji.movereservation.springboot.domain.aparts.Aparts;
import com.hadoyaji.movereservation.springboot.domain.aparts.ApartsRepository;
import com.hadoyaji.movereservation.springboot.domain.user.UserRepository;
import com.hadoyaji.movereservation.springboot.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ApartsRepository apartsRepository;

    /**
     * @author      : hagyuho
     * @date        : 2021. 05. 21
     * @method      : save
     * @description : 유저생성
     */
    @Transactional
    public Long save(UserSaveRequestDto requestDto){

        Aparts aparts = apartsRepository.findByDongAndHo(requestDto.getDong(), requestDto.getHo());
        Long id = userRepository.save(requestDto.toEntity(aparts)).getId();

        return id;
    }

}
