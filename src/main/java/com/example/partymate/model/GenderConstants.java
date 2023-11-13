package com.example.partymate.model;

/**
 * @author : Unagi_zoso
 * @date : 2023-10-12
 */
public enum GenderConstants {
    MALE("MALE"), FEMALE("FEMALE");

    private final String string;

    GenderConstants(String string) {
        this.string = string;
    }

    public static GenderConstants convertTo(String genderString) {
        return GenderConstants.valueOf(genderString.toUpperCase());
    }
}
