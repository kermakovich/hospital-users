package solvd.laba.ermakovich.hu.web.controller;

import jakarta.validation.groups.Default;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.command.CreateDoctorCommand;
import solvd.laba.ermakovich.hu.command.DoctorCommandService;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.query.DoctorQueryService;
import solvd.laba.ermakovich.hu.web.dto.DoctorDto;
import solvd.laba.ermakovich.hu.web.dto.group.OnCreate;
import solvd.laba.ermakovich.hu.web.mapper.DoctorMapper;

/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequestMapping("users-api/v1/doctors")
@RequiredArgsConstructor
@Slf4j
public final class DoctorController {

    private final DoctorMapper doctorMapper;
    private final DoctorCommandService commandService;
    private final DoctorQueryService queryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> create(@Validated({OnCreate.class, Default.class})
                                     @RequestBody final DoctorDto doctorDto) {
        Doctor doctor = doctorMapper.toEntity(doctorDto);
        return commandService.handle(new CreateDoctorCommand(doctor));
    }

    @GetMapping
    public Mono<Boolean> isExistByExternalId(
            @RequestParam final UUID externalId
    ) {
        return queryService.isExistByExternalId(externalId);
    }

}
