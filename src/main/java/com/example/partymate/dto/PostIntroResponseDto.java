package com.example.partymate.dto;

import com.example.partymate.model.CategoryConstants;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import static lombok.AccessLevel.PROTECTED;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-12
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
public class PostIntroResponseDto {
    private Long postId;
    private Long partyId;
    private String title;
    private String nickname;
    private String thumbnailImageUrl;
    private Integer currentPartyMemberCount;
    private Integer maxPartyMemberCount;
    private LocalDate duration;
    private CategoryConstants categoryName;

    @QueryProjection
    public PostIntroResponseDto(
            Long postId,
            Long partyId,
            String title,
            String nickname,
            LocalDate duration,
            String thumbnailImageUrl,
            Integer currentPartyMemberCount,
            Integer maxPartyMemberCount,
            CategoryConstants categoryName
    ) {
        this.postId = postId;
        this.partyId = partyId;
        this.title = title;
        this.nickname = nickname;
        this.duration = duration;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.currentPartyMemberCount = currentPartyMemberCount;
        this.maxPartyMemberCount = maxPartyMemberCount;
        this.categoryName = categoryName;
    }
}
