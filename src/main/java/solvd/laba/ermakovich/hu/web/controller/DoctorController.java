package solvd.laba.ermakovich.hu.web.controller;

import jakarta.validation.groups.Default;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.domain.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.domain.command.CreateDoctorCommand;
import solvd.laba.ermakovich.hu.domain.command.DeleteDoctorCommand;
import solvd.laba.ermakovich.hu.service.command.DoctorCommandService;
import solvd.laba.ermakovich.hu.service.query.DoctorQueryService;
import solvd.laba.ermakovich.hu.web.dto.DoctorAggregateDto;
import solvd.laba.ermakovich.hu.web.dto.DoctorDto;
import solvd.laba.ermakovich.hu.web.dto.DoctorSearchCriteriaDto;
import solvd.laba.ermakovich.hu.web.dto.group.OnCreate;
import solvd.laba.ermakovich.hu.web.mapper.DoctorMapper;
import solvd.laba.ermakovich.hu.web.mapper.DoctorSearchCriteriaMapper;

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
    private final DoctorSearchCriteriaMapper criteriaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> create(@Validated({OnCreate.class, Default.class})
                             @RequestBody final DoctorDto doctorDto) {
        Doctor doctor = doctorMapper.toEntity(doctorDto);
        return commandService.handle(new CreateDoctorCommand(doctor));
    }

    @GetMapping
    public Flux<DoctorAggregateDto> getByCriteria(
            final DoctorSearchCriteriaDto criteriaDto,
            @RequestParam(required = false, defaultValue = "0") final int page,
            @RequestParam(required = false, defaultValue = "5") final int size
    ) {
        var criteria = criteriaMapper.toEntity(criteriaDto);
        Flux<DoctorAggregate> flux = queryService.findAllByCriteria(
                criteria,
                PageRequest.of(page, size)
        );
        return doctorMapper.fluxToDto(flux);
    }

    @DeleteMapping
    public Mono<Void> deleteByExternalId(
            @RequestParam final UUID externalId
    ) {
        return commandService.handle(new DeleteDoctorCommand(externalId));
    }

}
