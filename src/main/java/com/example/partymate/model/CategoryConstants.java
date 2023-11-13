package com.example.partymate.model;

/**
 * @author Unagi_zoso
 * @since 2023-11-12
 */
public enum CategoryConstants {
    TECH,
    SPORTS,
    MUSIC;

    public static CategoryConstants fromString(String text) {
        for (CategoryConstants categoryName : CategoryConstants.values()) {
            if (categoryName.toString().equalsIgnoreCase(text)) {
                return categoryName;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
