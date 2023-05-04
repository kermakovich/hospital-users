package solvd.laba.ermakovich.hu.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import solvd.laba.ermakovich.hu.domain.UserRole;
import solvd.laba.ermakovich.hu.web.dto.group.OnCreate;
import solvd.laba.ermakovich.hu.web.dto.group.OnCreateAppointment;
import solvd.laba.ermakovich.hu.web.dto.group.OnCreateReview;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */

@Getter
@Setter
public abstract class UserInfoDto {

    @NotNull(groups = {OnCreateAppointment.class, OnCreateReview.class},
            message = "can not be null")
    private Long id;
    private UUID externalId;


    @NotBlank(groups = OnCreate.class, message = "can`t be empty")
    @Size(min = 1, max = 35, message = "length should be in 1..35")
    private String name;

    @NotBlank(groups = OnCreate.class, message = "can`t be empty")
    @Size(min = 1, max = 35, message = "length should be in 1..35",
            groups = OnCreate.class)
    private String surname;

    @NotBlank(groups = OnCreate.class, message = "can`t be empty")
    @Size(min = 1, max = 35, message = "length should be in 1..35",
            groups = OnCreate.class)
    private String fatherhood;

    @NotNull(groups = OnCreate.class, message = "can`t be null")
    @Past(groups = OnCreate.class, message = "can`t be in the future")
    private LocalDate birthday;

    @NotBlank(groups = OnCreate.class, message = "can`t be empty")
    @Size(min = 3, max = 320, message = "length should be in 3..320",
            groups = OnCreate.class)
    private String email;

    @NotBlank(groups = OnCreate.class, message = "can`t be empty")
    @Size(min = 4, max = 20, message = "length should be in 4..20",
            groups = OnCreate.class)
    private String password;

    @NotNull(groups = OnCreate.class, message = "can`t be null")
    private UserRole role;

}

