package solvd.laba.ermakovich.hu.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import solvd.laba.ermakovich.hu.domain.event.integration.CreateElasticDoctor;
import solvd.laba.ermakovich.hu.domain.event.integration.DeleteElasticDoctor;
import solvd.laba.ermakovich.hu.domain.event.integration.IntegrationEvent;
import solvd.laba.ermakovich.hu.domain.exception.IllegalOperationException;
import solvd.laba.ermakovich.hu.service.event.elastic.ElasticDoctorEventService;
import solvd.laba.ermakovich.hu.service.event.kafka.KafkaDoctorHandler;

/**
 * @author Ermakovich Kseniya
 */
@ExtendWith(MockitoExtension.class)
final class KafkaDoctorHandlerTest extends BaseTest {

    @Mock
    ElasticDoctorEventService elasticEventService;

    @InjectMocks
    KafkaDoctorHandler kafkaDoctorHandler;

    @Test
    @SneakyThrows
    void verifiesCreate() {
        var event = new IntegrationEvent();
        event.setEventType("createElasticDoctor");
        event.setPayload(new ObjectMapper()
                .writeValueAsString(BaseTest.doctor)
        );
        var expectedCreateDoctor = new CreateElasticDoctor(BaseTest.doctor);
        CreateElasticDoctor actual = kafkaDoctorHandler.createElasticDoctor(event);
        Assertions.assertEquals(
                expectedCreateDoctor,
                actual,
                "events are not equal");
    }

    @Test
    @SneakyThrows
    void verifiesDelete() {
        var event = new IntegrationEvent();
        var externalId = UUID.randomUUID();
        event.setEventType("deleteElasticDoctor");
        event.setPayload(new ObjectMapper()
                .writeValueAsString(externalId)
        );
        var expectedDeleteDoctor = new DeleteElasticDoctor(externalId);
        DeleteElasticDoctor actual = kafkaDoctorHandler.deleteElasticDoctor(event);
        Assertions.assertEquals(
                expectedDeleteDoctor,
                actual,
                "events are not equal");
    }

    @Test
    @SneakyThrows
    void verifiesWhenCreateDoctor() {
        var event = new IntegrationEvent();
        event.setEventType("createElasticDoctor");
        event.setPayload(new ObjectMapper()
                .writeValueAsString(BaseTest.doctor)
        );
        Mockito.doReturn(Mono.just(
                        BaseTest.doctor
                ))
                .when(elasticEventService)
                .create(
                        Mockito.any(CreateElasticDoctor.class)
                );
        StepVerifier.create(
                kafkaDoctorHandler.when(event)
                )
                .verifyComplete();
        Mockito.verify(elasticEventService, Mockito.times(1))
                .create(Mockito.any(CreateElasticDoctor.class));
    }

    @Test
    @SneakyThrows
    void verifiesWhenDeleteDoctor() {
        var event = new IntegrationEvent();
        event.setEventType("deleteElasticDoctor");
        event.setPayload(new ObjectMapper()
                .writeValueAsString(BaseTest.doctor.getExternalId())
        );
        Mockito.doReturn(Mono.empty())
                .when(elasticEventService)
                .delete(
                        Mockito.any(DeleteElasticDoctor.class)
                );
        StepVerifier.create(
                        kafkaDoctorHandler.when(event)
                )
                .verifyComplete();
        Mockito.verify(elasticEventService, Mockito.times(1))
                .delete(Mockito.any(DeleteElasticDoctor.class));
    }

    @Test
    void verifiesWhenWrongEventType() {
        var event = new IntegrationEvent();
        event.setEventType("wrongEventType");
        Assertions.assertThrows(IllegalOperationException.class,
                () -> kafkaDoctorHandler.when(event),
                "Exception wasn`t thrown");

    }

}
