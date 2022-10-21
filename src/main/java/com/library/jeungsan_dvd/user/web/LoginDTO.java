package com.library.jeungsan_dvd.user.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class LoginDTO {

    @NotEmpty
    private String userIdentity;

    @NotEmpty
    private String password;
}
