package com.example.partymate.testutils;

import com.example.partymate.model.Category;
import static com.example.partymate.model.CategoryConstants.TECH;
import com.example.partymate.model.Post;

/**
 * @author : Unagi_zoso
 * @date : 2023-11-12
 */
public class CategoryHelper {
    public static Category generateCategory(){
        return Category.builder()
                .categoryName(TECH)
                .post(PostHelper.generatePost())
                .build();
    }

    public static Category generateCategory(Post post){
        return Category.builder()
                .categoryName(TECH)
                .post(post)
                .build();
    }
}
