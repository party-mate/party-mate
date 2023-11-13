package com.example.partymate.dto;

import java.util.List;

/**
 * @author Unagi_zoso
 * @since 2023-11-12
 */
public class PostResponseDtoList {
    private final List<PostResponseDto> postResponseDtoList;

    public PostResponseDtoList(List<PostResponseDto> postResponseList) {
        this.postResponseDtoList = List.copyOf(postResponseList);
    }

    public List<PostResponseDto> getList() {
        return postResponseDtoList;
    }
}
