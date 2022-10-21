package com.library.jeungsan_dvd.item.web;

import com.library.jeungsan_dvd.item.service.DvdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class DvdController {

    private final DvdService dvdService;

    @GetMapping("/dvd/add")
    public String addDvdForm() {
        return "dvd/addForm";
    }

    @GetMapping("/dvds/{dvdId}")
    public String dvd(@PathVariable("dvdId") Long dvdId) {
        return "dvd/dvd";
    }

    @GetMapping("/dvds")
    public String dvdList() {
        dvdService

        return "dvd/dvdList";
    }
}
