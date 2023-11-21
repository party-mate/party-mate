package com.example.partymate.service;

import com.example.partymate.dto.MemberPartyRequestDto;
import com.example.partymate.dto.PartySaveRequestDto;
import com.example.partymate.model.Member;
import com.example.partymate.model.Party;
import static com.example.partymate.model.Post.toPost;

import com.example.partymate.model.CategoryConstants;
import com.example.partymate.dto.PostIntroResponseDto;
import com.example.partymate.dto.PostResponseDto;
import com.example.partymate.dto.PostSaveRequestDto;
import com.example.partymate.model.Post;
import com.example.partymate.repository.PostRepository;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Unagi_zoso
 * @since 2023-11-13
 */
@RequiredArgsConstructor
@Service
public class PostService {
    private final MemberService memberService;
    private final PostRepository postRepository;
    private final PartyService partyService;
    private final CategoryService categoryService;
    private final MemberPartyService memberPartyService;
    private final CaptionImageService captionImageService;
    private final EntityManager entityManager;

    @Transactional
    public void savePost(PostSaveRequestDto postSaveRequestDto, String username) {
        System.out.println(postSaveRequestDto.getMaxPartyMemberCount() + " " + postSaveRequestDto.getContent() + " " + postSaveRequestDto.getDuration() + " " +  postSaveRequestDto.getTitle());
        Party party = partyService.saveParty(new PartySaveRequestDto("", postSaveRequestDto.getMaxPartyMemberCount()));
        System.out.println("난가 " + username);
        Member member = memberService.findMemberByUsername(username);
        Post post = postRepository.save(toPost(postSaveRequestDto, party, member));
        memberPartyService.partyAddMember(new MemberPartyRequestDto(member.getMemberId(), party.getPartyId()));
        categoryService.saveCategory(postSaveRequestDto.getCategory(), post);

        // MultipartFile captionImage = postSaveRequestDto.getCaptionImage();
        // if (captionImage != null) {
        //    captionImageService.saveCaptionImage(new CaptionImageSaveDto(post.getPostId(), captionImage));
        //}
    }

    public Page<PostIntroResponseDto> findPostIntroResponseDtoListwithPage(LocalDate duration, PageRequest pageRequest) {
        return postRepository.findPagePostIntroInDuration(duration, pageRequest);
    }

    public Page<PostIntroResponseDto> findPostIntroResponseDtoListByCategoryWithPage(CategoryConstants categoryName, PageRequest pageRequest) {
        return postRepository.findPagePostIntroByCategory(categoryName, pageRequest);
    }

    public PostResponseDto findPostResponseDto(Long postId) {
        return postRepository.findPostById(postId);
    }
}
