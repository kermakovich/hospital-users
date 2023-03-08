package solvd.laba.ermakovich.hu.web.controller;

import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.domain.UserInfo;
import solvd.laba.ermakovich.hu.service.DoctorService;
import solvd.laba.ermakovich.hu.web.dto.DoctorDto;
import solvd.laba.ermakovich.hu.web.dto.group.OnCreate;
import solvd.laba.ermakovich.hu.web.mapper.DoctorMapper;

/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequestMapping("api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDto create(@Validated({OnCreate.class, Default.class}) @RequestBody DoctorDto doctorDto) {
        Doctor doctor = doctorMapper.toEntity(doctorDto);
        UserInfo userInfo = doctorMapper.toUserEntity(doctorDto);
        doctorService.create(doctor, userInfo);
        return doctorMapper.toDto(doctor);
    }

}
