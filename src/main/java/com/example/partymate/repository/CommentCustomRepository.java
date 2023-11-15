package com.example.partymate.repository;

import com.example.partymate.dto.CommentResponseDtoList;

/**
 * @author Unagi_zoso
 * @since 2023-11-15
 */
public interface CommentCustomRepository {
    CommentResponseDtoList findCommentsByPostId(Long postId);
}
