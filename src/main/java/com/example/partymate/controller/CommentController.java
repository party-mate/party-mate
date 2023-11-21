package com.example.partymate.controller;

import com.example.partymate.dto.CommentResponseDtoList;
import com.example.partymate.dto.CommentRequestDto;
import com.example.partymate.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public void saveComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal String username) {
        commentService.saveComment(commentRequestDto, username);
    }

    @GetMapping("/post/{postId}")
    public CommentResponseDtoList findCommentListByPostId(@PathVariable(name = "postId") Long postId) {
        return commentService.findCommentListByPostId(postId);
    }

    @PatchMapping("/update/{commentId}")
    public void updateComment(@PathVariable(name = "commentId") Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal String email) {
        commentService.updateComment(commentId, commentRequestDto, email);
    }

    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable(name = "commentId") Long commentId, @AuthenticationPrincipal String email) {
        System.out.println(commentId + " " + email);
        commentService.deleteComment(commentId, email);
    }
}
