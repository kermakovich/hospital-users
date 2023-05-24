package solvd.laba.ermakovich.hu.service;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import solvd.laba.ermakovich.hu.domain.DoctorSearchCriteria;
import solvd.laba.ermakovich.hu.domain.aggregate.AggregateStatus;
import solvd.laba.ermakovich.hu.domain.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.helper.BaseTest;
import solvd.laba.ermakovich.hu.repository.elastic.ElasticDoctorRepository;
import solvd.laba.ermakovich.hu.repository.mongo.DoctorRepository;
import solvd.laba.ermakovich.hu.service.query.DoctorQueryHandler;


/**
 * @author Ermakovich Kseniya
 */
@ExtendWith(MockitoExtension.class)
final class DoctorQueryHandlerTest extends BaseTest {

    @Mock
    DoctorRepository doctorRepository;

    @Mock
    ElasticDoctorRepository elasticDoctorRepository;

    @InjectMocks
    DoctorQueryHandler queryHandler;

    private static final PageRequest DEFAULT_PAGEABLE = PageRequest.of(0,5);

    @Test
    void verifiesDoctorExistByEmail() {
        final var email = "pomodorov@mail.ru";
        Mockito.doReturn(Mono.just(true))
                .when(doctorRepository)
                .existsByDoctorEmail(Mockito.any(String.class));
        StepVerifier.create(
                queryHandler.isExistByEmail(email)
                )
                .assertNext((isExist) ->
                        Assertions.assertEquals(true, isExist)
                )
                .verifyComplete();
        Mockito.verify(doctorRepository, Mockito.times(1))
                .existsByDoctorEmail(Mockito.any(String.class));
    }

    @Test
    void verifiesFindByExternalId() {
        final var expectedDoctorAggregate = BaseTest.doctorAggregate;
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
    void verifiesFindById() {
        final var expectedDoctorAggregate = BaseTest.doctorAggregate;
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
    void createsAggregateIfDoesNotExistById() {
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
    void verifiesFindAllByCriteria() {
        final var criteria = BaseTest.doctorSearchCriteria;
        Mockito.doReturn(Flux.just(BaseTest.elasticDoctor,
                        BaseTest.elasticDoctor)
                )
                .when(elasticDoctorRepository)
                .findAllByCriteria(criteria, DEFAULT_PAGEABLE);
        Mockito.doReturn(Mono.just(BaseTest.doctorAggregate))
                .when(doctorRepository)
                .findByDoctorExternalId(Mockito.any(UUID.class));
        Flux<DoctorAggregate> actualFlux =  queryHandler.findAllByCriteria(
                criteria,
                DEFAULT_PAGEABLE
        );
        StepVerifier.create(actualFlux)
                .expectNext(BaseTest.doctorAggregate)
                .expectNextCount(1)
                .verifyComplete();
        Mockito.verify(elasticDoctorRepository, Mockito.times(1))
                .findAllByCriteria(Mockito.any(DoctorSearchCriteria.class),
                        Mockito.any(Pageable.class));
        Mockito.verify(doctorRepository, Mockito.times(2))
                .findByDoctorExternalId(Mockito.any(UUID.class));
    }

}
