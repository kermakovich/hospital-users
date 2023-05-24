package solvd.laba.ermakovich.hu.service.operation;

import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import solvd.laba.ermakovich.hu.domain.event.integration.CreateElasticDoctor;
import solvd.laba.ermakovich.hu.domain.event.integration.IntegrationEvent;
import solvd.laba.ermakovich.hu.helper.BaseTest;
import solvd.laba.ermakovich.hu.service.kafka.KafkaEventHandlerImpl;
import solvd.laba.ermakovich.hu.service.kafka.operation.CreateDoctorOperation;
import solvd.laba.ermakovich.hu.service.kafka.operation.Operation;


/**
 * @author Ermakovich Kseniya
 */
@ExtendWith(MockitoExtension.class)
final class KafkaEventHandlerImplTest extends BaseTest {

    @Mock
    CreateDoctorOperation createDoctorOperation;

    KafkaEventHandlerImpl kafkaEventHandler;


    @Test
    void reactsOnCreateDoctorWithOperations() {
        var operations = new HashMap<String, Operation>(1);
        operations.put("createDoctor", createDoctorOperation);
        kafkaEventHandler = new KafkaEventHandlerImpl(operations);
        IntegrationEvent event = new CreateElasticDoctor(BaseTest.elasticDoctor);
        Mockito.doReturn(Mono.empty())
                .when(createDoctorOperation)
                .on(Mockito.any(IntegrationEvent.class));
        StepVerifier.create(kafkaEventHandler.onEventReceived(event))
                .verifyComplete();
        Mockito.verify(createDoctorOperation, Mockito.times(1))
                .on(Mockito.any(IntegrationEvent.class));
    }

}
