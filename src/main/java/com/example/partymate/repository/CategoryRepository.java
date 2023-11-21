package com.example.partymate.repository;

import com.example.partymate.model.Category;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-12
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE category SET category.erased_flag = 1 WHERE category.category_id = :categoryId", nativeQuery = true)
    void turnOnErasedFlagById(Long categoryId);
}
