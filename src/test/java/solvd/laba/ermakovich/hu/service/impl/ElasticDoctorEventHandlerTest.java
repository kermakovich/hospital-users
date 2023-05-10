package solvd.laba.ermakovich.hu.service.impl;

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
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.elastic.ElasticDoctorRepository;
import solvd.laba.ermakovich.hu.event.elastic.ElasticDoctorEventHandler;
import solvd.laba.ermakovich.hu.event.integration.CreateElasticDoctor;
import solvd.laba.ermakovich.hu.event.integration.DeleteElasticDoctor;


/**
 * @author Ermakovich Kseniya
 */
@ExtendWith(MockitoExtension.class)
final class ElasticDoctorEventHandlerTest {

    @Mock
    ElasticDoctorRepository elasticDoctorRepository;

    @InjectMocks
    ElasticDoctorEventHandler elasticDoctorEventHandler;

    @Test
    void verifyCreateTest() {
       var event = new CreateElasticDoctor(TestDoctorFactory.getDoctor());
        Mockito.doReturn(Mono.just(TestDoctorFactory.getDoctor()))
               .when(elasticDoctorRepository)
               .save(Mockito.any(Doctor.class));
       Mono<Doctor> actualDoctor = elasticDoctorEventHandler
               .create(event);
       StepVerifier.create(actualDoctor)
                .assertNext((doctor) ->
                        Assertions.assertEquals(event.getDoctor(), doctor,
                                "doctors are not equal"))
                .verifyComplete();
        Mockito.verify(elasticDoctorRepository, Mockito.times(1))
                .save(Mockito.any(Doctor.class));
    }

    @Test
    void verifyDeleteTest() {
        var event = new DeleteElasticDoctor(UUID.randomUUID());
        Mockito.doReturn(Mono.empty())
                .when(elasticDoctorRepository)
                .deleteByExternalId(Mockito.any(UUID.class));
        Mono<Void> voidMono = elasticDoctorEventHandler.delete(event);
        StepVerifier.create(voidMono)
                .verifyComplete();
        Mockito.verify(elasticDoctorRepository, Mockito.times(1))
                .deleteByExternalId(Mockito.any(UUID.class));
    }

}
