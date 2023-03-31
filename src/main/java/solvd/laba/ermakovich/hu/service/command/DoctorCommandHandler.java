package solvd.laba.ermakovich.hu.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.exception.ResourceAlreadyExistsException;
import solvd.laba.ermakovich.hu.service.event.CreateAccountEvent;
import solvd.laba.ermakovich.hu.service.event.CreateDoctorEvent;
import solvd.laba.ermakovich.hu.service.event.DoctorEventService;
import solvd.laba.ermakovich.hu.kafka.KafkaProducer;
import solvd.laba.ermakovich.hu.service.query.DoctorQueryService;

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
    public Mono<String> handle(CreateDoctorCommand command) {
        command.getDoctor().setPassword(
                hashPassword(command.getDoctor().getPassword()));

        return queryService.isExistByEmail(command.getDoctor().getEmail())
                .flatMap(isExist -> {
                    if (Boolean.TRUE.equals(isExist)) {
                        throw new ResourceAlreadyExistsException("User with this email: " + command.getDoctor().getEmail() + " already exist");
                    } else {
                        CreateDoctorEvent event = new CreateDoctorEvent(command.getAggregateId(), command.getDoctor());
                        eventService.on(event);

                        CreateAccountEvent accountEvent = new CreateAccountEvent(command.getDoctor().getExternalId());
                        kafkaProducer.send(accountEvent);
                        return Mono.just(event.getAggregateId());
                    }
                });
    }

    private String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

}
