package com.library.jeungsan_dvd.user.service;

import com.library.jeungsan_dvd.user.domain.User;
import com.library.jeungsan_dvd.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserRepository userRepository;

    //로그인 유저 찾아서 user 리턴 없으면 null 리턴
    public User login(String loginId, String password) {
        return userRepository.findByIdentity(loginId)
                .stream().filter(user -> user.getPassword().equals(password))
                .findAny().orElse(null);
    }
}
