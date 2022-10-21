package com.library.jeungsan_dvd.item.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class DVDTest {

    @Test
    void 조회수_증가() {
        //given
        DVD dvd = new DVD();

        //when
        dvd.updateViewCount();

        //then
        assertThat(dvd.getViewCnt()).isEqualTo(1);
    }

    @Test
    void 수정_메소드() {

        //given
        DVD dvd = DVD.builder()
                .title("Title")
                .registNumber("JL0")
                .genre(Genre.ACTION)
                .director("이신광")
                .runningTime(123)
                .summary("Summary")
                .build();

        //when
        dvd.changeTitle("제목");
        dvd.changeRegistNumber("JL100");
        dvd.changeGenre(Genre.COMEDY);
        dvd.changeDirector("Lee");
        dvd.changeRunningTime(60);
        dvd.changeSummary("요약");

        //then
        assertThat(dvd.getTitle()).isEqualTo("제목");
        assertThat(dvd.getRegistNumber()).isEqualTo("JL100");
        assertThat(dvd.getGenre()).isEqualTo(Genre.COMEDY);
        assertThat(dvd.getDirector()).isEqualTo("Lee");
        assertThat(dvd.getRunningTime()).isEqualTo(60);
        assertThat(dvd.getSummary()).isEqualTo("요약");
    }

}