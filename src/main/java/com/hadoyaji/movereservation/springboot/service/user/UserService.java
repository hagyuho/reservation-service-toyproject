package com.hadoyaji.movereservation.springboot.service.user;

import com.hadoyaji.movereservation.springboot.domain.aparts.ApartsRepository;
import com.hadoyaji.movereservation.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ApartsRepository apartsRepository;

//    /**
//     * @author : hagyuho
//     * @date : 2021. 05. 21
//     * @method : save
//     * @description : 유저생성
//     */
//    @Transactional
//    public Long save(UserSaveRequestDto requestDto) throws Exception {
//
//        User user = userRepository.findByHpNumber(requestDto.getHpNumber());
//
//        if (ObjectUtils.isEmpty(user)) {
//            Aparts aparts = apartsRepository.findByDongAndHo(requestDto.getDong(), requestDto.getHo());
//            Long id = userRepository.save(requestDto.toEntity(aparts)).getId();
//            return id;
//        } else {
//            throw new IllegalArgumentException("이미 등록된 핸드폰번호가 있습니다.. hpNumber=" + requestDto.getHpNumber());
//        }
//
//    }

//    /**
//     * @author : hagyuho
//     * @date : 2021. 05. 21
//     * @method : signin
//     * @description : 로그인
//     */
//    @Transactional(readOnly = true)
//    public UserSigninResponseDto signin(UserSigninRequestDto requestDto) {
//        User user = userRepository.findByHpNumberAndPassword(requestDto.getHpNumber(), requestDto.getPassword());
//        if (!ObjectUtils.isEmpty(user)) {
//            return new UserSigninResponseDto(user);
//        } else {
//            throw new IllegalArgumentException("회원정보가 없습니다.. hpNumber=" + requestDto.getHpNumber());
//        }
//
//    }

}
