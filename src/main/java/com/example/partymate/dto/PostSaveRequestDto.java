package com.example.partymate.dto;

import com.example.partymate.model.CategoryConstants;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Unagi_zoso
 * @since 2023-11-13
 */
@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String content;
    private CategoryConstants category;
    private Integer maxPartyMemberCount;
    private LocalDate duration;
}
