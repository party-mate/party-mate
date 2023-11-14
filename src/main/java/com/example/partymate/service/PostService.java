package com.example.partymate.service;

import static com.example.partymate.model.Post.toPost;

import com.example.partymate.dto.PostSaveRequestDto;
import com.example.partymate.model.Post;
import com.example.partymate.dto.CaptionImageSaveDto;
import com.example.partymate.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Unagi_zoso
 * @since 2023-11-13
 */
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CaptionImageService captionImageService;

    @Transactional
    public void savePost(PostSaveRequestDto postSaveRequestDto) {
        Post post = postRepository.save(toPost(postSaveRequestDto));
        MultipartFile captionImage = postSaveRequestDto.getCaptionImage();
        if (captionImage != null) {
            captionImageService.saveCaptionImage(new CaptionImageSaveDto(post.getPostId(), captionImage));
        }
    }
}
