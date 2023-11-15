package com.example.partymate.controller;

import com.example.partymate.dto.CommentResponseDtoList;
import com.example.partymate.dto.CommentSaveDto;
import com.example.partymate.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Unagi_zoso
 * @since 2023-11-15
 */
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public void saveComment(CommentSaveDto commentSaveDto, UserDetails userDetails) {
        commentService.saveComment(commentSaveDto, userDetails);
    }

    @GetMapping("/{postId}")
    public CommentResponseDtoList findCommentListByPostId(@PathVariable(name = "postId") Long postId) {
        return commentService.findCommentListByPostId(postId);
    }
}
