package solvd.laba.ermakovich.hu.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import solvd.laba.ermakovich.hu.domain.Department;
import solvd.laba.ermakovich.hu.domain.PatientAges;
import solvd.laba.ermakovich.hu.domain.Specialization;
import solvd.laba.ermakovich.hu.web.dto.group.OnCreate;

/**
 * @author Ermakovich Kseniya
 */
@Getter
@Setter
public class DoctorDto extends UserInfoDto {

    @NotNull(message = "can`t be null", groups = OnCreate.class)
    private Department department;

    @NotNull(message = "can`t be null", groups = OnCreate.class)
    private Specialization specialization;

    private Integer cabinet;
    private BigDecimal pricePerHour;

    @Min(value = 0, message = "can't be negative", groups = OnCreate.class)
    private Float experience;
    private PatientAges patientAges;

}
