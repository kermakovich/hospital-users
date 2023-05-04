package solvd.laba.ermakovich.hu.command;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.aggregate.DoctorAggregateService;
import solvd.laba.ermakovich.hu.domain.exception.ResourceAlreadyExistsException;
import solvd.laba.ermakovich.hu.event.CreateAccount;
import solvd.laba.ermakovich.hu.event.CreateDoctor;
import solvd.laba.ermakovich.hu.event.DoctorEventService;
import solvd.laba.ermakovich.hu.event.EventRoot;
import solvd.laba.ermakovich.hu.event.IntegrationEvent;
import solvd.laba.ermakovich.hu.kafka.producer.KafkaProducer;
import solvd.laba.ermakovich.hu.query.DoctorQueryService;

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
                        IntegrationEvent accountEvent = new CreateAccount(
                                command.getDoctor().getExternalId(),
                                command.getAggregateId()
                        );
                        kafkaProducer.send(accountEvent);
                        return aggregateService.applyCreateOperation(
                                createDoctor
                                )
                                .then();
                    }
                });
    }

}
