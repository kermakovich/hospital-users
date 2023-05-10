package solvd.laba.ermakovich.hu.service.impl;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import solvd.laba.ermakovich.hu.aggregate.AggregateStatus;
import solvd.laba.ermakovich.hu.service.aggregate.DoctorAggregateService;
import solvd.laba.ermakovich.hu.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.command.CreateDoctorCommand;
import solvd.laba.ermakovich.hu.command.DeleteDoctorCommand;
import solvd.laba.ermakovich.hu.service.command.DoctorCommandHandler;
import solvd.laba.ermakovich.hu.domain.exception.ResourceAlreadyExistsException;
import solvd.laba.ermakovich.hu.service.event.DoctorEventService;
import solvd.laba.ermakovich.hu.event.EventRoot;
import solvd.laba.ermakovich.hu.event.integration.CreateElasticDoctor;
import solvd.laba.ermakovich.hu.event.integration.DeleteElasticDoctor;
import solvd.laba.ermakovich.hu.kafka.producer.KafkaProducer;
import solvd.laba.ermakovich.hu.service.query.DoctorQueryService;


/**
 * @author Ermakovich Kseniya
 */
@ExtendWith(MockitoExtension.class)
final class DoctorCommandHandlerTest {

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    DoctorQueryService doctorQueryService;

    @Mock
    DoctorEventService eventService;

    @Mock
    KafkaProducer kafkaProducer;

    @Mock
    DoctorAggregateService doctorAggregateService;

    @InjectMocks
    DoctorCommandHandler doctorCommandHandler;

    @Test
    void verifyDoctorCreateSuccessfulTest() {
        var command = new CreateDoctorCommand(TestDoctorFactory.getDoctor());
        Mockito.doReturn("")
                .when(bCryptPasswordEncoder)
                .encode(Mockito.any(String.class));
        Mockito.doReturn(Mono.just(false))
                .when(doctorQueryService)
                .isExistByEmail(Mockito.any(String.class));
        Mockito.doNothing()
                .when(eventService)
                .create(Mockito.any(EventRoot.class));
        Mockito.doNothing()
                .when(kafkaProducer)
                .synchronizeElastic(Mockito.any(CreateElasticDoctor.class));
        Mockito.doReturn(Mono.just(new DoctorAggregate(
                UUID.randomUUID().toString(),
                AggregateStatus.APPROVED
        )))
                .when(doctorAggregateService)
                .create(Mockito.any(EventRoot.class));
        Mono<Void> actualMono = doctorCommandHandler.handle(command);
        StepVerifier.create(actualMono)
                .verifyComplete();
        Mockito.verify(eventService, Mockito.times(1))
                .create(Mockito.any(EventRoot.class));
        Mockito.verify(doctorQueryService, Mockito.times(1))
                .isExistByEmail(Mockito.any(String.class));
        Mockito.verify(doctorAggregateService, Mockito.times(1))
                .create(Mockito.any(EventRoot.class));
    }


    @Test
    void verifyDoctorCreateWithAlreadyExistsTest() {
        var command = new CreateDoctorCommand(
                TestDoctorFactory.getDoctor()
        );
        Mockito.doReturn("").when(bCryptPasswordEncoder)
                .encode(Mockito.any(String.class));
        Mockito.doReturn(Mono.just(true))
                .when(doctorQueryService)
                .isExistByEmail(Mockito.any(String.class));
        Mono<Void> actualMono = doctorCommandHandler.handle(command);
        StepVerifier.create(actualMono)
                .expectError(ResourceAlreadyExistsException.class)
                .verify();
    }

    @Test
    void verifyDoctorDeleteTest() {
        var command = new DeleteDoctorCommand(UUID.randomUUID());
        Mockito.doReturn(Mono.just(TestDoctorFactory.getDoctorAggregate()))
                .when(doctorQueryService)
                .findByExternalId(Mockito.any(UUID.class));
        Mockito.doNothing()
                .when(eventService)
                .create(Mockito.any(EventRoot.class));
        Mockito.doNothing()
                .when(kafkaProducer)
                .synchronizeElastic(Mockito.any(DeleteElasticDoctor.class));
        Mockito.doReturn(Mono.empty())
                .when(doctorAggregateService)
                .delete(Mockito.any(EventRoot.class));
        Mono<Void> actualMono = doctorCommandHandler.handle(command);
        StepVerifier.create(actualMono)
                .verifyComplete();
        Mockito.verify(eventService, Mockito.times(1))
                .create(Mockito.any(EventRoot.class));
        Mockito.verify(doctorQueryService, Mockito.times(1))
                .findByExternalId(Mockito.any(UUID.class));
        Mockito.verify(doctorAggregateService, Mockito.times(1))
                .delete(Mockito.any(EventRoot.class));
    }

}
