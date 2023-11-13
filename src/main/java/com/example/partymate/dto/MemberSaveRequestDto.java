package com.example.partymate.dto;

import com.example.partymate.model.Agreement;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Unagi_zoso
 * @since : 2023-11-12
 */
@Getter
public class MemberSaveRequestDto {
    private String password;
    private String emailAddress;
    private MultipartFile profileImage;
    private String phoneNumber;
    private String nickname;
    private String gender;
    private LocalDate birthYearDate;
    private Agreement agreement;
}
