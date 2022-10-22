package com.library.jeungsan_dvd.item.service;

import com.library.jeungsan_dvd.item.domain.FileEntity;
import com.library.jeungsan_dvd.item.domain.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FileService {

    private final FileRepository fileRepository;

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    @Transactional
    public FileEntity saveFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalName = multipartFile.getOriginalFilename();
        String savedFileName = createSavedFileName(originalName);

        //실제 로컬에 uuid 파일명으로 저장
        multipartFile.transferTo(new File(getFullPath(savedFileName)));

        //originalName과 savedName를 가진 엔티티 생성
        FileEntity file = FileEntity.builder()
                .originalName(originalName)
                .savedName(savedFileName)
                .build();

        //데이터베이스에 저장
        FileEntity savedFile = fileRepository.save(file);

        return savedFile;
    }

    /**
     * 파일의 저장 이름 만드는 메소드 uuid + . + 확장자
     * @param originalName
     * @return
     */
    private String createSavedFileName(String originalName) {
        String ext = extractExt(originalName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    /** 확장자 추출 메소드 */
    private String extractExt(String originalName) {
        int pos = originalName.lastIndexOf(".");
        return originalName.substring(pos + 1);
    }
}
