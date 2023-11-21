package com.example.partymate.service;

import com.example.partymate.model.Category;
import com.example.partymate.model.CategoryConstants;
import com.example.partymate.model.Post;
import com.example.partymate.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Unagi_zoso
 * @since 2023-11-20
 */
@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void saveCategory(CategoryConstants category, Post post) {

        categoryRepository.save(new Category(category, post));
    }
}
