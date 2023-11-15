package com.example.partymate.dto;

import java.util.List;

/**
 * @author Unagi_zoso
 * @since 2023-11-15
 */
public class CommentResponseDtoList {
    private final List<CommentResponseDto> commentResponseDtoList;

    public CommentResponseDtoList(List<CommentResponseDto> commentResponseDtoList) {
        this.commentResponseDtoList = commentResponseDtoList;
    }

    public List<CommentResponseDto> getCommentResponseDtoList() {
        return commentResponseDtoList;
    }
}
