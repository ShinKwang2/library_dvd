package com.library.jeungsan_dvd.item.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Test
    void 확장자_추출() {
        //given
        String originalName = "hello.png";

        //when

    }

}