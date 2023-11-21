package com.example.partymate.model;

/**
 * @author Unagi_zoso
 * @since 2023-11-12
 */
public enum CategoryConstants {
    CERTIFICATION("자격증"),
    PARTY("공모전"),
    CAREER("취업"),
    ETC("기타");

    private final String categoryName;

    CategoryConstants(String categoryName) {
        this.categoryName = categoryName;
    }

    public static CategoryConstants fromString(String text) {
        for (CategoryConstants categoryName : CategoryConstants.values()) {
            if (categoryName.toString().equalsIgnoreCase(text)) {
                return categoryName;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }

    public static String toString(CategoryConstants category) {
        return category.categoryName;
    }
}
