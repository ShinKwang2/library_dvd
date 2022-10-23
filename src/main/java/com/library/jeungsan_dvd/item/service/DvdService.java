package com.library.jeungsan_dvd.item.service;

import com.library.jeungsan_dvd.item.domain.DVD;
import com.library.jeungsan_dvd.item.domain.DvdQueryRepository;
import com.library.jeungsan_dvd.item.domain.DvdRepository;
import com.library.jeungsan_dvd.item.web.DvdResponseDTO;
import com.library.jeungsan_dvd.item.web.PopularDvdResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
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
        return dvdRepository.findAll(Sort.by(Sort.Direction.ASC, "title")).stream()
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

    public DvdResponseDTO getResponseDTO(Long dvdId) {
        DVD findDvd = dvdRepository.findById(dvdId)
                .orElseThrow(() -> new IllegalStateException("해당 DVD가 없습니다."));
        DvdResponseDTO dvdResponseDTO = makeResponseDTO(findDvd);
        return dvdResponseDTO;
    }



    /** DVD 등록 */
    @Transactional
    public Long regist(DVD dvd) {
        dvdRepository.save(dvd);
        return dvd.getId();
    }

    /** DVD 수정 */
    @Transactional
    public Long update() {
        return null;
    }

    /** DVD 삭제 */
    @Transactional
    public void delete(DVD dvd) {
        dvdRepository.delete(dvd);
    }


    /** DVD entity -> DvdResponseDTO */
    private DvdResponseDTO makeResponseDTO(DVD findDvd) {
        return DvdResponseDTO.builder()
                .registNumber(findDvd.getRegistNumber())
                .title(findDvd.getTitle())
                .dvdType(findDvd.getDvdType())
                .genre(findDvd.getGenre())
                .director(findDvd.getDirector())
                .runningTime(findDvd.getRunningTime())
                .summary(findDvd.getSummary())
                .viewCnt(findDvd.getViewCnt())
                .build();
    }
}
