package com.example.partymate.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Unagi_zoso
 * @since : 2023-11-13
 */
@Getter
@RequiredArgsConstructor
public class CaptionImageSaveDto {
    private final Long postId;
    private final MultipartFile captionImage;
}
