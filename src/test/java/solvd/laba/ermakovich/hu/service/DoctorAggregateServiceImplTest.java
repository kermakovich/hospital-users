package solvd.laba.ermakovich.hu.service;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import solvd.laba.ermakovich.hu.domain.aggregate.AggregateStatus;
import solvd.laba.ermakovich.hu.domain.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.domain.event.CreateDoctor;
import solvd.laba.ermakovich.hu.domain.event.DeleteDoctor;
import solvd.laba.ermakovich.hu.domain.event.EventRoot;
import solvd.laba.ermakovich.hu.repository.mongo.DoctorRepository;
import solvd.laba.ermakovich.hu.service.aggregate.DoctorAggregateServiceImpl;
import solvd.laba.ermakovich.hu.service.query.DoctorQueryHandler;



/**
 * @author Ermakovich Kseniya
 */
@ExtendWith(MockitoExtension.class)
final class DoctorAggregateServiceImplTest {

    @Mock
    DoctorQueryHandler doctorQueryService;

    @Mock
    DoctorRepository doctorRepository;

    @InjectMocks
    DoctorAggregateServiceImpl doctorAggregateService;

    @Test
    void verifiesDoctorAggregateCreate() {
        var aggregateId = UUID.randomUUID().toString();
        Mono<DoctorAggregate> expectedMono = Mono.just(
                new DoctorAggregate(
                    aggregateId,
                    AggregateStatus.APPROVED
                )
        );
        EventRoot eventRoot = new CreateDoctor(aggregateId, BaseTest.doctor);
        DoctorAggregate expectedDoctorAggr = new DoctorAggregate(
                aggregateId,
                AggregateStatus.APPROVED
        );
        expectedDoctorAggr.setDoctor(BaseTest.doctor);
        Mockito.doReturn(expectedMono)
                .when(doctorQueryService)
                .findByIdOrCreate(
                        Mockito.any(String.class)
                );
        Mockito.doReturn(expectedMono)
                .when(doctorRepository)
                .save(Mockito.any(DoctorAggregate.class));
        Mono<DoctorAggregate> actualMono = doctorAggregateService.create(eventRoot);
        StepVerifier.create(actualMono)
                .assertNext((actualAggregate) ->
                        Assertions.assertEquals(expectedDoctorAggr,
                                actualAggregate,
                                "aggregates are not equal"))
                .verifyComplete();
        Mockito.verify(doctorQueryService, Mockito.times(1))
                .findByIdOrCreate( Mockito.any(String.class));
        Mockito.verify(doctorRepository, Mockito.times(1))
                .save( Mockito.any(DoctorAggregate.class));
    }

    @Test
    void verifiesDoctorAggregateDelete() {
        EventRoot eventRoot = new DeleteDoctor(UUID.randomUUID().toString());
        Mockito.when(doctorRepository.deleteById(eventRoot.getAggregateId()))
                .thenReturn(Mono.empty());
        StepVerifier.create(doctorAggregateService.delete(eventRoot))
                .verifyComplete();
        Mockito.verify(doctorRepository, Mockito.times(1))
                .deleteById(eventRoot.getAggregateId());
    }

}
