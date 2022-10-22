package com.library.jeungsan_dvd.item.web;

import com.library.jeungsan_dvd.item.domain.DVD;
import com.library.jeungsan_dvd.item.domain.FileEntity;
import com.library.jeungsan_dvd.item.service.DvdService;
import com.library.jeungsan_dvd.item.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class DvdController {

    private final DvdService dvdService;
    private final FileService fileService;

    @GetMapping("/admin/new")
    public String addDvdForm() {
        return "dvd/addForm";
    }

    @PostMapping("/admin/new")
    public String saveDvd(@ModelAttribute DvdRequestDto dvdRequestDto, RedirectAttributes redirectAttributes) throws IOException {

        FileEntity savedFile = fileService.saveFile(dvdRequestDto.getFile());
        DVD dvd = makeDvd(dvdRequestDto, savedFile);
        Long savedDvdId = dvdService.regist(dvd);

        redirectAttributes.addAttribute("dvdId", savedDvdId);

        return "redirect:/dvds/{dvdId}";
    }



    @GetMapping("/dvds/{dvdId}")
    public String dvd(@PathVariable("dvdId") Long dvdId) {
        return "dvd/dvd-view";
    }

    @GetMapping("/dvds")
    public String dvdList() {


        return "dvd/dvdList";
    }


    //DTO에 toEntity 메소드를 맏늘려고 했으나 FileEntity 와 MultipartFile 타입불일치로 인해 메소드를 여기서 뽑음
    private DVD makeDvd(DvdRequestDto dvdRequestDto, FileEntity savedFile) {
        return DVD.builder()
                .title(dvdRequestDto.getTitle())
                .registNumber(dvdRequestDto.getRegistNumber())
                .director(dvdRequestDto.getDirector())
                .runningTime(dvdRequestDto.getRunningTime())
                .summary(dvdRequestDto.getSummary())
                .file(savedFile)
                .build();
    }
}
