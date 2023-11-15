package com.example.partymate.repository;

import com.example.partymate.dto.CommentResponseDtoList;
import com.example.partymate.dto.QCommentResponseDto;
import com.example.partymate.model.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

/**
 * @author Unagi_zoso
 * @since 2023-11-15
 */
@Repository
public class CommentCustomRepositoryImpl implements CommentCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    public CommentCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public CommentResponseDtoList findCommentsByPostId(Long postId) {
        return new CommentResponseDtoList(new ArrayList<>(jpaQueryFactory.select(new QCommentResponseDto(
                QComment.comment.commentId,
                QComment.comment.content,
                QComment.comment.member.memberId,
                QComment.comment.post.postId,
                QComment.comment.parentComment.commentId
                ))
                .from(QComment.comment)
                .where(QComment.comment.post.postId.eq(postId))
                .where(QComment.comment.erasedFlag.eq(0))
                .fetch()));
    }
}
