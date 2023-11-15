package com.example.partymate.controller;

import com.example.partymate.dto.PostIntroResponseDto;
import com.example.partymate.dto.PostResponseDto;
import com.example.partymate.dto.PostSaveRequestDto;
import com.example.partymate.service.PostService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Unagi_zoso
 * @since 2023-11-15
 */
@RequiredArgsConstructor
@RequestMapping("/api/post")
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping
    public void savePost(@RequestBody PostSaveRequestDto postSaveRequestDto) {
        postService.savePost(postSaveRequestDto);
    }

    @GetMapping("/intro")
    public Page<PostIntroResponseDto> findPostIntroByPage (
            LocalDate duration,
            @RequestParam(name = "requestedPageId") Integer requestedPageId,
            @RequestParam(name = "pageSize") Integer pageSize) {
            return postService.findPostIntroResponseDtoListByPage(duration, PageRequest.of(requestedPageId, pageSize));
    }

    @GetMapping("/detail/{postId}")
    public PostResponseDto findPost(@PathVariable(name = "postId") Long postId) {
        return postService.findPostResponseDto(postId);
    }
}
