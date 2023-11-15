package com.example.partymate.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Unagi_zoso
 * @since 2023-11-15
 */
@NoArgsConstructor
@Getter
public class CommentResponseDto {
    private Long commentId;
    private String content;
    private Long memberId;
    private Long postId;
    private Long parentCommentId;

    @QueryProjection
    public CommentResponseDto(Long commentId, String content, Long memberId, Long postId, Long parentCommentId) {
        this.commentId = commentId;
        this.content = content;
        this.memberId = memberId;
        this.postId = postId;
        this.parentCommentId = parentCommentId;
    }
}
