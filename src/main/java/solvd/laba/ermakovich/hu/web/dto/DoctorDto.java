package solvd.laba.ermakovich.hu.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import solvd.laba.ermakovich.hu.domain.Department;
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

}
