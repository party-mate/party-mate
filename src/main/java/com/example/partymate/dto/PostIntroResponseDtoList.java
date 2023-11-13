package com.example.partymate.dto;

import java.util.List;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-12
 */
public class PostIntroResponseDtoList {
    private final List<PostIntroResponseDto> postIntroResponseDtoList;

    public PostIntroResponseDtoList(List<PostIntroResponseDto> postIntroResponseDtoList) {
        this.postIntroResponseDtoList = List.copyOf(postIntroResponseDtoList);
    }

    public int getSize() {
        return postIntroResponseDtoList.size();
    }

    public PostIntroResponseDto get(int index) {
        return postIntroResponseDtoList.get(index);
    }

    public List<PostIntroResponseDto> getImmutableList() {
        return postIntroResponseDtoList;
    }
}
