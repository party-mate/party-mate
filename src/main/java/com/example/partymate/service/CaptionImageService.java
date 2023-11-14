package com.example.partymate.service;

import static com.example.partymate.model.CaptionImage.toCaptionImage;

import com.example.partymate.properties.FileStorageProperties;
import com.example.partymate.dto.CaptionImageSaveDto;
import com.example.partymate.repository.CaptionImageRepository;
import com.example.partymate.repository.PostRepository;
import static com.example.partymate.utils.namestrategy.ImageUtil.resizeImage;
import static com.example.partymate.utils.namestrategy.ImageUtil.saveBufferedImageToFile;
import java.awt.image.BufferedImage;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Unagi_zoso
 * @since 2023-11-13
 */
@RequiredArgsConstructor
@Service
public class CaptionImageService {
    private final CaptionImageRepository captionImageRepository;
    private final PostRepository postRepository;

    // 다 트랜잭션 걸어줘야하나..
    public void saveCaptionImage(CaptionImageSaveDto captionImageSaveDto) {
        FileStorageProperties fileStorageProperties = new FileStorageProperties();
        FileStorageService fileStorageService = new FileStorageService(fileStorageProperties);
        MultipartFile multipartFile = captionImageSaveDto.getCaptionImage();
        String imageUrl = fileStorageService.storeFile(multipartFile);

        BufferedImage resizedImage = resizeImage(multipartFile, 300, 300);
        String resizeImageUrl = fileStorageProperties.getUploadDir() + fileStorageService.generateFileName("resized" + multipartFile.getOriginalFilename());
        saveBufferedImageToFile(resizedImage, resizeImageUrl);

        captionImageRepository.save(toCaptionImage(imageUrl, resizeImageUrl, postRepository.getReferenceById(captionImageSaveDto.getPostId())));
    }
}
