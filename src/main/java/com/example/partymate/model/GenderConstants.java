package com.example.partymate.model;

/**
 * @author Unagi_zoso
 * @since 2023-10-12
 */
public enum GenderConstants {
    MALE("MALE"), FEMALE("FEMALE");

    private final String gender;

    GenderConstants(String gender) {
        this.gender = gender;
    }

    public static GenderConstants convertTo(String genderString) {
        return GenderConstants.valueOf(genderString.toUpperCase());
    }
}
