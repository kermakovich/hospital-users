package solvd.laba.ermakovich.hu.web.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;
import solvd.laba.ermakovich.hu.web.dto.group.OnCreate;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class PatientAgesDto {

    @Min(value = 0, message = "can't be negative", groups = OnCreate.class)
    private Integer from;

    @Min(value = 0, message = "can't be negative", groups = OnCreate.class)
    private Integer to;

}
