package solvd.laba.ermakovich.hu.web.dto;

import lombok.Getter;

@Getter
public class FieldErrorDto extends ErrorDto {

    private final String fieldName;

    public FieldErrorDto(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }
}
