package solvd.laba.ermakovich.hu.command;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.exception.ResourceAlreadyExistsException;
import solvd.laba.ermakovich.hu.event.CreateAccount;
import solvd.laba.ermakovich.hu.event.CreateDoctor;
import solvd.laba.ermakovich.hu.event.DoctorEventService;
import solvd.laba.ermakovich.hu.event.Event;
import solvd.laba.ermakovich.hu.kafka.KafkaProducer;
import solvd.laba.ermakovich.hu.query.DoctorQueryService;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class DoctorCommandHandler implements DoctorCommandService {

    private final DoctorEventService eventService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DoctorQueryService queryService;
    private final KafkaProducer kafkaProducer;

    @Override
    @SneakyThrows
    public Mono<String> handle(CreateDoctorCommand command) {
        command.getDoctor().setPassword(
                hashPassword(command.getDoctor().getPassword()));
        return queryService.isExistByEmail(command.getDoctor().getEmail())
                .flatMap(isExist -> {
                    if (Boolean.TRUE.equals(isExist)) {
                        throw new ResourceAlreadyExistsException("User with this email: " +
                                command.getDoctor().getEmail() + " already exist");
                    } else {
                        Event createDoctor = new CreateDoctor(command.getAggregateId(), command.getDoctor());
                        eventService.when(createDoctor);
                        Event accountEvent = new CreateAccount(command.getDoctor().getExternalId());
                        kafkaProducer.send(accountEvent);
                        return Mono.just(createDoctor.getAggregateId());
                    }
                });
    }

    private String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

}
