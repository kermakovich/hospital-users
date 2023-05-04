package solvd.laba.ermakovich.hu.web.dto;

import lombok.Getter;

@Getter
public class FieldErrorDto extends ErrorDto {

    private final String fieldName;

    public FieldErrorDto(final String fieldName, final String message) {
        super(message);
        this.fieldName = fieldName;
    }
}
