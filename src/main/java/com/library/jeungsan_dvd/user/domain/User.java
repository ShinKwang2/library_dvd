package com.library.jeungsan_dvd.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long Id;

    @Column(name = "user_identity", nullable = false)
    private String userIdentity; //유저 아이디 ex) sklee0206
    @Column(nullable = false)
    private String password; //유저 비밀번호

    @Column(nullable = false)
    private String userName; //유저 이름

    @Enumerated(EnumType.STRING)
    private Gender gender; //유저 성별

    @Enumerated(EnumType.STRING)
    private UserRole userRole; //유저 : 일반유저, ADMIN유저

    private String email;

    @Builder
    public User(String userIdentity, String password, String userName, Gender gender, String email) {
        this.userIdentity = userIdentity;
        this.password = password;
        this.userName = userName;
        this.gender = gender;
        this.email = email;
    }


    //== 수정 메소드 ==//
    public void changeRole(UserRole changeRole) {
        this.userRole = changeRole;
    }
}
