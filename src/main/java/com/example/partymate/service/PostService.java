package com.example.partymate.service;

import static com.example.partymate.model.Post.toPost;

import com.example.partymate.dto.PostSaveRequestDto;
import com.example.partymate.domain.FileStorageProperties;
import com.example.partymate.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Unagi_zoso
 * @since 2023-11-13
 */
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    public void savePost(PostSaveRequestDto postSaveRequestDto) {
        if (postSaveRequestDto.getCaptionImage() != null) {
            FileStorageService fileStorageService = new FileStorageService(new FileStorageProperties());
            String imageUrl = fileStorageService.storeFile(postSaveRequestDto.getCaptionImage());
        }

        postRepository.save(toPost(postSaveRequestDto));
    }
}
