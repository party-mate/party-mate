package com.example.partymate.repository;

import com.example.partymate.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Unagi_zoso
 * @since 2023-11-15
 */
public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {
}
