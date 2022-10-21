package com.library.jeungsan_dvd.item.web;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PopularDvdResponseDTO {

    private String title;

    private String registNumber;

    private String summary;

    private Integer runningTime;

    @Builder
    public PopularDvdResponseDTO(String title, String registNumber, String summary, Integer runningTime) {
        this.title = title;
        this.registNumber = registNumber;
        this.summary = summary;
        this.runningTime = runningTime;
    }
}
