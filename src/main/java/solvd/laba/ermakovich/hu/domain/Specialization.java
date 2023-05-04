package solvd.laba.ermakovich.hu.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Ermakovich Kseniya
 */
@Getter
@RequiredArgsConstructor
public enum Specialization {

    DENTIST("dentist"),
    THERAPIST("therapist"),
    ENDOCRINOLOGIST("endocrinologist"),
    PHYSIOTHERAPIST("physiotherapist");

    private final String value;

    @Override
    public String toString() {
        return value.toUpperCase();
    }

}
