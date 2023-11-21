package com.example.partymate.repository;

import com.example.partymate.model.Comment;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Unagi_zoso
 * @since 2023-11-15
 */
public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {

    @Transactional
    @Modifying
    @Query(value = "UPDATE comment SET comment.erased_flag = 1 WHERE comment.comment_id = :commentId", nativeQuery = true)
    void turnOnErasedFlagById(Long commentId);
}
