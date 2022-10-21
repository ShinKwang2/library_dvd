package com.library.jeungsan_dvd;

import com.library.jeungsan_dvd.item.domain.DVD;
import com.library.jeungsan_dvd.item.domain.DvdType;
import com.library.jeungsan_dvd.item.domain.Genre;
import com.library.jeungsan_dvd.user.domain.Gender;
import com.library.jeungsan_dvd.user.domain.User;
import com.library.jeungsan_dvd.user.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Component
public class IniDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @RequiredArgsConstructor
    @Component
    @Transactional
    static class InitService {

        private final EntityManager em;

        public void dbInit() {
            User userA = User.builder()
                    .userIdentity("sklee0206")
                    .userName("이신광")
                    .password("1234")
                    .email("sklee0206@naver.com")
                    .gender(Gender.MAN)
                    .build();
            userA.changeRole(UserRole.ADMIN);
            em.persist(userA);

            for (int i = 0; i < 10; i++) {
                DVD dvd = DVD.builder()
                        .registNumber("JL" + i)
                        .title("반지의 제왕 " + i)
                        .dvdType(DvdType.DVD)
                        .genre(Genre.FANTASY)
                        .runningTime(120 + i)
                        .summary("반지의 제왕 " + i)
                        .director("피터 잭슨")
                        .build();
                em.persist(dvd);
            }

        }
    }
}
