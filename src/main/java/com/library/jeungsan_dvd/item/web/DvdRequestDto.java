package com.library.jeungsan_dvd.item.web;

import com.library.jeungsan_dvd.item.domain.DVD;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DvdRequestDto {

    private String title;
    private String registNumber;
    private String director;
    private Integer runningTime;
    private String summary;
    private MultipartFile file;

    //private DvdType dvdType;
    //private Genre genre;
}
