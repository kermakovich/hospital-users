package solvd.laba.ermakovich.hu.service;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import solvd.laba.ermakovich.hu.domain.aggregate.AggregateStatus;
import solvd.laba.ermakovich.hu.domain.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.repository.elastic.ElasticDoctorRepository;
import solvd.laba.ermakovich.hu.repository.mongo.DoctorRepository;
import solvd.laba.ermakovich.hu.service.query.DoctorQueryHandler;


/**
 * @author Ermakovich Kseniya
 */
@ExtendWith(MockitoExtension.class)
final class DoctorQueryHandlerTest {

    @Mock
    DoctorRepository doctorRepository;

    @Mock
    ElasticDoctorRepository elasticDoctorRepository;

    @InjectMocks
    DoctorQueryHandler queryHandler;

    @Test
    void verifyIsExistByEmailTest() {
        final var email = "pomodorov@mail.ru";
        Mockito. doReturn(Mono.just(true))
                .when(elasticDoctorRepository)
                .existsByEmail(Mockito.any(String.class));
        StepVerifier.create(
                queryHandler.isExistByEmail(email)
                )
                .assertNext((isExist) ->
                        Assertions.assertEquals(true, isExist)
                )
                .verifyComplete();
        Mockito.verify(elasticDoctorRepository, Mockito.times(1))
                .existsByEmail(Mockito.any(String.class));
    }

    @Test
    void verifyFindByExternalIdTest() {
        final var expectedDoctorAggregate = TestDoctorFactory.getDoctorAggregate();
        Mockito.doReturn(Mono.just(expectedDoctorAggregate))
                .when(doctorRepository)
                .findByDoctorExternalId(Mockito.any(UUID.class));
        StepVerifier.create(
                        queryHandler.findByExternalId(
                                expectedDoctorAggregate
                                        .getDoctor()
                                        .getExternalId()
                        )
                )
                .assertNext(actualDoctorAggregate ->
                        Assertions.assertEquals(
                                expectedDoctorAggregate,
                                actualDoctorAggregate)
                )
                .verifyComplete();
        Mockito.verify(doctorRepository, Mockito.times(1))
                .findByDoctorExternalId(Mockito.any(UUID.class));
    }

    @Test
    void verifyFindByIdTest() {
        final var expectedDoctorAggregate = TestDoctorFactory.getDoctorAggregate();
        Mockito.doReturn(Mono.just(expectedDoctorAggregate))
                .when(doctorRepository)
                .findById(Mockito.any(String.class));
        StepVerifier.create(
                        queryHandler.findByIdOrCreate(
                                expectedDoctorAggregate.getId()
                        )
                )
                .assertNext(actualDoctorAggregate ->
                        Assertions.assertEquals(
                                expectedDoctorAggregate,
                                actualDoctorAggregate)
                )
                .verifyComplete();
        Mockito.verify(doctorRepository, Mockito.times(1))
                .findById(Mockito.any(String.class));
    }

    @Test
    void verifyCreateIfDoesNotExistByIdTest() {
        final var expectedDoctorAggregate =  new DoctorAggregate(
                UUID.randomUUID().toString(),
                AggregateStatus.APPROVED
        );
        Mockito.doReturn(Mono.empty())
                .when(doctorRepository)
                .findById(Mockito.any(String.class));
        StepVerifier.create(
                        queryHandler.findByIdOrCreate(expectedDoctorAggregate.getId())
                )
                .assertNext(actualDoctorAggregate ->
                        Assertions.assertEquals(
                                expectedDoctorAggregate,
                                actualDoctorAggregate)
                )
                .verifyComplete();
        Mockito.verify(doctorRepository, Mockito.times(1))
                .findById(Mockito.any(String.class));
    }

    @Test
    void verifyFindAllBySurnameTest() {
        final var surname = "pomidorov";
        final var doctorList = List.of(
                TestDoctorFactory.getDoctor(),
                TestDoctorFactory.getDoctor()
        );
        Mockito.doReturn(Flux.just(doctorList))
                .when(elasticDoctorRepository)
                .findAllBySurname(Mockito.any(String.class));
        StepVerifier.create(
                        queryHandler.findAllBySurname(surname),
                        doctorList.size()
                )
                .expectNextCount(doctorList.size())
                .expectComplete();
        Mockito.verify(elasticDoctorRepository, Mockito.times(1))
                .findAllBySurname(Mockito.any(String.class));
    }

}
