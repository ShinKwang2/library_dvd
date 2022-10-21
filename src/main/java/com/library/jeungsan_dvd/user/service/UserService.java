package com.library.jeungsan_dvd.user.service;

import com.library.jeungsan_dvd.user.domain.User;
import com.library.jeungsan_dvd.user.domain.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    /** 회원 가입 */
    @Transactional
    public Long signup(User user) {
        userRepository.save(user);
        return user.getId();
    }

    /** 중복 아이디 검증 */
    public boolean validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByIdentity(user.getUserIdentity());
        if (!findUsers.isEmpty()) {
            return true;
        }
        return false;
    }

    /** 전체 회원 조회 Admin 용으로만 사용할 것 */
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    /** 회원 한명 검색 Admin 용으로만 사용할 것 */
    public User findOne(Long userId) {
        return userRepository.findOne(userId);
    }
}
