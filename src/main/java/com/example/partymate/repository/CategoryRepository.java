package com.example.partymate.repository;

import com.example.partymate.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-12
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
