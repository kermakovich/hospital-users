package solvd.laba.ermakovich.hu.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Ermakovich Kseniya
 */
@Getter
@RequiredArgsConstructor
public enum Department {

    SURGERY("surgery"),
    THERAPEUTIC("therapeutic"),
    DENTAL("dental");

    private final String value;

    @Override
    public String toString() {
        return value.toUpperCase();
    }

}
