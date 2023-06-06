package solvd.laba.ermakovich.hu.service.command;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.command.CreateDoctorCommand;
import solvd.laba.ermakovich.hu.domain.command.DeleteDoctorCommand;
import solvd.laba.ermakovich.hu.domain.event.CreateDoctor;
import solvd.laba.ermakovich.hu.domain.event.DeleteDoctor;
import solvd.laba.ermakovich.hu.domain.event.EventRoot;
import solvd.laba.ermakovich.hu.domain.event.integration.CreateElasticDoctor;
import solvd.laba.ermakovich.hu.domain.event.integration.DeleteElasticDoctor;
import solvd.laba.ermakovich.hu.domain.exception.ResourceAlreadyExistsException;
import solvd.laba.ermakovich.hu.kafka.producer.KafkaProducer;
import solvd.laba.ermakovich.hu.service.aggregate.DoctorAggregateService;
import solvd.laba.ermakovich.hu.service.event.DoctorEventService;
import solvd.laba.ermakovich.hu.service.query.DoctorQueryService;
import solvd.laba.ermakovich.hu.web.mapper.DoctorMapper;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public final class DoctorCommandHandler implements DoctorCommandService {

    private final DoctorEventService eventService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DoctorQueryService queryService;
    private final KafkaProducer kafkaProducer;
    private final DoctorAggregateService aggregateService;
    private final DoctorMapper doctorMapper;

    @Override
    @SneakyThrows
    public Mono<Void> handle(final CreateDoctorCommand command) {
        command.getDoctor().setPassword(
                bCryptPasswordEncoder.encode(
                        command.getDoctor().getPassword()
                )
        );
        return queryService.isExistByEmail(command.getDoctor().getEmail())
                .flatMap(isExist -> {
                    if (Boolean.TRUE.equals(isExist)) {
                        throw new ResourceAlreadyExistsException(
                                "User with this email: "
                                        + command.getDoctor().getEmail()
                                        + " already exist"
                        );
                    } else {
                        EventRoot createDoctor = new CreateDoctor(
                                command.getAggregateId(), command.getDoctor()
                        );
                        eventService.create(createDoctor);
                        var elasticDoctor = doctorMapper.toElastic(
                                command.getDoctor()
                        );
                        kafkaProducer.synchronizeElastic(
                                new CreateElasticDoctor(elasticDoctor)
                        );
                        return aggregateService.create(
                                        createDoctor
                                )
                                .then();
                    }
                });
    }

    @Override
    public Mono<Void> handle(final DeleteDoctorCommand command) {
        return queryService.findByExternalId(command.getExternalId())
                .flatMap(doctorAggregate -> {
                    EventRoot eventRoot = new DeleteDoctor(
                            doctorAggregate.getId()
                    );
                    eventService.create(eventRoot);
                    kafkaProducer.synchronizeElastic(
                            new DeleteElasticDoctor(command.getExternalId())
                    );
                    return aggregateService.delete(eventRoot);
                });
    }

}
