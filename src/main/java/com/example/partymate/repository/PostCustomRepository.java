package com.example.partymate.repository;

import com.example.partymate.dto.PostResponseDtoList;
import com.example.partymate.dto.PostIntroResponseDto;
import com.example.partymate.dto.PostResponseDto;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Unagi_zoso
 * @since 2023-11-08
 */
public interface PostCustomRepository {
    PostResponseDto findPostByPartyId(Long partyId);
    PostResponseDtoList findPostsByMemberId(Long memberId);
    Page<PostIntroResponseDto> findPagePostIntroInDuration(LocalDate duration, Pageable pageable);
    Page<PostIntroResponseDto> findPageAllPostIntro(Pageable pageable);
}
