package com.example.partymate.dto;

import com.example.partymate.model.CaptionImage;
import com.example.partymate.model.CategoryConstants;
import com.example.partymate.model.Post;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import static lombok.AccessLevel.PROTECTED;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Unagi_zoso
 * @since 2023-11-12
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
public class PostResponseDto {
    private Long postId;
    private String title;
    private String nickname;
    private String content;
    private LocalDate duration;
    private String category;
    private Integer currentPartyMemberCount;
    private Integer maxPartyMemberCount;

    @QueryProjection
    public PostResponseDto(
            Long postId,
            String title,
            String nickname,
            String content,
            LocalDate duration,
            CategoryConstants category,
            Integer currentPartyMemberCount,
            Integer maxPartyMemberCount
    ) {
        this.postId = postId;
        this.title = title;
        this.nickname = nickname;
        this.content = content;
        this.duration = duration;
        this.category = CategoryConstants.toString(category);
        this.currentPartyMemberCount = currentPartyMemberCount;
        this.maxPartyMemberCount = maxPartyMemberCount;
    }
}
