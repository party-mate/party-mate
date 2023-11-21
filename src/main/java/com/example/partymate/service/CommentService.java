package com.example.partymate.service;

import com.example.partymate.dto.CommentResponseDtoList;
import com.example.partymate.dto.CommentRequestDto;
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

    public void saveComment(CommentRequestDto commentRequestDto, String email) {
        Member member = memberRepository.findMemberByEmail(email).toEntity();
        System.out.println(member.getMemberId() + " " + member.getNickname());
        System.out.println(commentRequestDto.getPostId() + " " + commentRequestDto.getParentCommentId() + " " + commentRequestDto.getContent());
        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(RuntimeException::new);
        commentRepository.save(new Comment(commentRequestDto.getContent(), member, post, null));
    }

    public CommentResponseDtoList findCommentListByPostId(Long postId) {
        return commentRepository.findCommentsByPostId(postId);
    }

    public void updateComment(Long commentId, CommentRequestDto commentRequestDto, String email) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(RuntimeException::new);
        Member member = memberRepository.findMemberByEmail(email).toEntity();
        if (comment.getMember().getMemberId().equals(member.getMemberId())) {
            comment.setContent(commentRequestDto.getContent());
            commentRepository.save(comment);
        }
    }

    public void deleteComment(Long commentId, String email) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(RuntimeException::new);
        Member member = memberRepository.findMemberByEmail(email).toEntity();

        System.out.println(comment.getMember().getMemberId() + " " + member.getMemberId());
        if (comment.getMember().getMemberId().equals(member.getMemberId())) {
            commentRepository.turnOnErasedFlagById(commentId);
        }
    }
}
