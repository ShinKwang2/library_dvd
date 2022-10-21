package com.library.jeungsan_dvd.item.web;

import com.library.jeungsan_dvd.item.domain.DvdType;
import com.library.jeungsan_dvd.item.domain.Genre;
import lombok.*;

@Getter @Setter
public class DvdResponseDTO {

    private String registNumber;
    private String title;
    private DvdType dvdType;
    private Genre genre;
    private String director;
    private Integer runningTime;
    private String summary;
    private long viewCnt;

    @Builder
    public DvdResponseDTO(String registNumber, String title, DvdType dvdType, Genre genre, String director, Integer runningTime, String summary, long viewCnt) {
        this.registNumber = registNumber;
        this.title = title;
        this.dvdType = dvdType;
        this.genre = genre;
        this.director = director;
        this.runningTime = runningTime;
        this.summary = summary;
        this.viewCnt = viewCnt;
    }
}
