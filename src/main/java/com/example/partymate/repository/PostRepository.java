package com.example.partymate.repository;

import com.example.partymate.model.Post;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Unagi_zoso
 * @since 2023-11-08
 */
public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {

    @Transactional
    @Modifying
    @Query(value = "UPDATE post SET .erased_flag = 1 WHERE post.post_id = :postId", nativeQuery = true)
    void turnOnErasedFlagById(Long postId);
}
