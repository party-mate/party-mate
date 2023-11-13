package com.example.partymate.repository;

import com.example.partymate.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-08
 */
public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
}
