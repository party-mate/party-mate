package com.example.partymate.service;

import com.example.partymate.properties.FileStorageProperties;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author : Unagi_zoso
 * @since : 2023-11-12
 */
@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            // Handle directory creation failure
            ex.printStackTrace();
        }
    }

    public String storeFile(MultipartFile file) {
        if (file == null) {
            throw new IllegalArgumentException("파일이 존재하지 않습니다.");
        }

        String fileName = generateFileName(file.getOriginalFilename());

        try {
            // 파일 저장 경로
            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            // 파일 복사
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return targetLocation.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            // Handle file storage exception
            return null;
        }
    }
    public String generateFileName(String fileName) {
        // 기존 파일명에서 확장자 추출
        String originalFileName = fileName;
        assert originalFileName != null;
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // 유니크한 파일명 생성
        return UUID.randomUUID().toString() + fileExtension;
    }
}




