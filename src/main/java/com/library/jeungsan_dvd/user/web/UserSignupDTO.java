package com.library.jeungsan_dvd.user.web;

import com.library.jeungsan_dvd.user.domain.Gender;
import com.library.jeungsan_dvd.user.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@ToString
public class UserSignupDTO {

    @NotBlank
    private String userIdentity;
    @NotBlank
    private String password;

    @NotBlank
    private String passwordCheck;
    @NotBlank
    private String username;
    private Gender gender;

    @NotBlank
    @Email
    private String email;

    public User toEntity() {
        return User.builder()
                .userIdentity(userIdentity)
                .password(password)
                .userName(username)
                .gender(gender)
                .email(email)
                .build();
    }


}
