package solvd.laba.ermakovich.hu.web.controller;

import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.command.CreateDoctorCommand;
import solvd.laba.ermakovich.hu.command.DoctorCommandService;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.query.DoctorQueryService;
import solvd.laba.ermakovich.hu.web.dto.AggregateDto;
import solvd.laba.ermakovich.hu.web.dto.DoctorDto;
import solvd.laba.ermakovich.hu.web.dto.group.OnCreate;
import solvd.laba.ermakovich.hu.web.mapper.DoctorMapper;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequestMapping("api/v1/doctors")
@RequiredArgsConstructor
@Slf4j
public class DoctorController {

    private final DoctorMapper doctorMapper;
    private final DoctorCommandService commandService;
    private final DoctorQueryService queryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AggregateDto> create(@Validated({OnCreate.class, Default.class})
                                     @RequestBody DoctorDto doctorDto) {
        Doctor doctor = doctorMapper.toEntity(doctorDto);
        return commandService.handle(new CreateDoctorCommand(doctor))
                .map(AggregateDto::new);
    }

    @GetMapping
    public Mono<Boolean> isExistByExternalId(@RequestParam UUID externalId) {
        return queryService.isExistByExternalId(externalId);
    }

}
