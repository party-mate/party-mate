package com.example.partymate.service;

import com.example.partymate.dto.CommentResponseDtoList;
import com.example.partymate.dto.CommentSaveDto;
import com.example.partymate.model.Comment;
import com.example.partymate.model.Member;
import com.example.partymate.model.Post;
import com.example.partymate.repository.CommentRepository;
import com.example.partymate.repository.MemberRepository;
import com.example.partymate.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Unagi_zoso
 * @since 2023-11-15
 */
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public void saveComment(CommentSaveDto commentSaveDto, UserDetails userDetails) {
        Member member = memberRepository.findMemberByNickname(userDetails.getUsername()).toEntity();
        Post post = postRepository.findById(commentSaveDto.getPostId()).orElseThrow(RuntimeException::new);
        Comment comment = commentRepository.findById(commentSaveDto.getParentCommentId()).orElseThrow(RuntimeException::new);
        commentRepository.save(new Comment(commentSaveDto.getContent(), member, post, comment));
    }

    public CommentResponseDtoList findCommentListByPostId(Long postId) {
        return commentRepository.findCommentsByPostId(postId);
    }
}
