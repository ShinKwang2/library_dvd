package com.library.jeungsan_dvd.item.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class DVD {

    @Id @GeneratedValue
    @Column(name = "dvd_id")
    private Long id;

    /** DVD 등록 번호 - 증산도서관에서 찾는 번호 */
    @Column(name = "regist_number", nullable = false)
    private String registNumber;

    @Column(name ="dvd_title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private DvdType dvdType; //DVD Type : DVD, BLUE-LAY

    @Enumerated(EnumType.STRING)
    private Genre genre; //장르

    @Column(nullable = false)
    private String director; //영화 감독

    @Column(nullable = false)
    private Integer runningTime;

    @Column(name = "dvd_summary")
    private String summary;

    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;

    @Column(name = "view_cnt")
    private long viewCnt; //뷰 카운트

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private FileEntity file;

    @Builder
    public DVD(String registNumber, String title, DvdType dvdType, Genre genre, String director, Integer runningTime, String summary, FileEntity file) {
        this.registNumber = registNumber;
        this.title = title;
        this.dvdType = dvdType;
        this.genre = genre;
        this.director = director;
        this.runningTime = runningTime;
        this.summary = summary;
        this.file =  file;
        this.createTime = LocalDateTime.now();
        this.lastModifiedTime = LocalDateTime.now();
        this.viewCnt = 0;
    }

    //== 수정 로직 ==//
    public void changeRegistNumber(String changeNumber) {
        this.registNumber = changeNumber;
    }
    public void changeTitle(String changeTitle) {
        this.title = changeTitle;
    }
    public void changeGenre(Genre genre) {
        this.genre = genre;
    }
    public void changeDirector(String changeDirector) {
        this.director = changeDirector;
    }
    public void changeRunningTime(Integer changRunningTime) {
        this.runningTime = changRunningTime;
    }
    public void changeSummary(String summary) {
        this.summary = summary;
    }

    //== 비즈니스 로직 ==/
    public void updateViewCount() {
        viewCnt++;
    }

}
