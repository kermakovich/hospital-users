package solvd.laba.ermakovich.hu.web.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.domain.DoctorSearchCriteria;
import solvd.laba.ermakovich.hu.domain.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.domain.command.CreateDoctorCommand;
import solvd.laba.ermakovich.hu.domain.command.DeleteDoctorCommand;
import solvd.laba.ermakovich.hu.service.command.DoctorCommandService;
import solvd.laba.ermakovich.hu.service.query.DoctorQueryService;
/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequiredArgsConstructor
public final class DoctorController {

    private final DoctorCommandService commandService;
    private final DoctorQueryService queryService;

    @MutationMapping
    public Mono<Void> create(@Argument final Doctor doctor) {
        return commandService.handle(new CreateDoctorCommand(doctor));
    }

    @QueryMapping
    public Flux<DoctorAggregate> getByCriteria(
            @Argument final DoctorSearchCriteria criteria,
            @Argument final int page,
            @Argument final int size
    ) {
        return queryService.findAllByCriteria(
                criteria,
                PageRequest.of(page, size)
        );
    }

    @MutationMapping
    public Mono<Void> deleteByExternalId(
            @Argument final UUID externalId
    ) {
        return commandService.handle(new DeleteDoctorCommand(externalId));
    }

}
