package com.library.jeungsan_dvd.item.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "file")
@Entity
public class FileEntity {

    @Id @GeneratedValue
    @Column(name="file_id")
    private Long id;

    private String originalName; //업로드한 이름

    private String savedName; //저장할 이름

    @Builder
    public FileEntity(String originalName, String savedName) {
        this.originalName = originalName;
        this.savedName = savedName;
    }


}
