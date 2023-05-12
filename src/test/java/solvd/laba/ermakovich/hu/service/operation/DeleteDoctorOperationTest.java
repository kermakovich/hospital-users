package solvd.laba.ermakovich.hu.service.operation;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import solvd.laba.ermakovich.hu.domain.event.integration.DeleteElasticDoctor;
import solvd.laba.ermakovich.hu.repository.elastic.ElasticDoctorRepository;
import solvd.laba.ermakovich.hu.helper.BaseTest;
import solvd.laba.ermakovich.hu.service.kafka.operation.DeleteDoctorOperation;

/**
 * @author Ermakovich Kseniya
 */
@ExtendWith(MockitoExtension.class)
final class DeleteDoctorOperationTest extends BaseTest {

    @Mock
    ElasticDoctorRepository elasticDoctorRepository;

    @InjectMocks
    DeleteDoctorOperation deleteDoctorOperation;

    @Test
    void reactsOnDeleteElasticDoctor() {
        var event = new DeleteElasticDoctor(UUID.randomUUID());
        Mockito.doReturn(Mono.empty()).when(elasticDoctorRepository)
                .deleteByExternalId(Mockito.any(UUID.class));
        StepVerifier.create(deleteDoctorOperation.on(event))
                .verifyComplete();
        Mockito.verify(elasticDoctorRepository, Mockito.times(1))
                .deleteByExternalId(Mockito.any(UUID.class));
    }

}
