package com.example.partymate.testutils;

import static com.example.partymate.testutils.PostHelper.generatePost;

import com.example.partymate.model.CaptionImage;
import com.example.partymate.model.Post;

/**
 * @author Unagi_zoso
 * @since 2023-11-12
 */
public class CaptionImageHelper {
    public static CaptionImage generateCaptionImage(){
        return CaptionImage.builder()
                .post(generatePost())
                .imageUrl("testImageUrl")
                .thumbnailImageUrl("testThumbnailImageUrl")
                .build();
    }

    public static CaptionImage generateCaptionImage(Post post){
        return CaptionImage.builder()
                .post(post)
                .imageUrl("testImageUrl")
                .thumbnailImageUrl("testThumbnailImageUrl")
                .build();
    }
}
