package com.library.jeungsan_dvd.item.service;

import com.library.jeungsan_dvd.item.domain.DVD;
import com.library.jeungsan_dvd.item.domain.DvdQueryRepository;
import com.library.jeungsan_dvd.item.domain.DvdRepository;
import com.library.jeungsan_dvd.item.web.DvdResponseDTO;
import com.library.jeungsan_dvd.item.web.PopularDvdResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DvdService {

    private final DvdRepository dvdRepository;
    private final DvdQueryRepository queryRepository;

    /** Popular DVD List */
    public List<PopularDvdResponseDTO> popularDvdList() {
        return queryRepository.findMostPopular().stream()
                .map(dvd -> PopularDvdResponseDTO.builder()
                        .title(dvd.getTitle())
                        .registNumber(dvd.getRegistNumber())
                        .summary(dvd.getSummary())
                        .runningTime(dvd.getRunningTime())
                        .build())
                .toList();
    }

    /** DVD LiSt 전체 */
    public List<DvdResponseDTO> dvdList() {
        return dvdRepository.findAllOrderByTitle().stream()
                .map(dvd -> DvdResponseDTO.builder()
                        .registNumber(dvd.getRegistNumber())
                        .title(dvd.getTitle())
                        .dvdType(dvd.getDvdType())
                        .genre(dvd.getGenre())
                        .director(dvd.getDirector())
                        .runningTime(dvd.getRunningTime())
                        .summary(dvd.getSummary())
                        .build()).toList();
    }

    /** DVD 등록 */
    public Long regist(DVD dvd) {
        dvdRepository.save(dvd);
        return dvd.getId();
    }

    /** DVD 수정 */
    public Long update() {
        return null;
    }

    /** DVD 삭제 */
    public void delete(DVD dvd) {
        dvdRepository.delete(dvd);
    }
}
