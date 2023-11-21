package com.example.partymate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Unagi_zoso
 * @since 2023-11-15
 */
@NoArgsConstructor
@Getter
public class CommentRequestDto {
    private String content;
    private Long postId;
    private Long parentCommentId;
}
