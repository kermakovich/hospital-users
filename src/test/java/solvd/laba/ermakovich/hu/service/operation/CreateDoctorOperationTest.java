package solvd.laba.ermakovich.hu.service.operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.domain.event.integration.CreateElasticDoctor;
import solvd.laba.ermakovich.hu.repository.elastic.ElasticDoctorRepository;
import solvd.laba.ermakovich.hu.helper.BaseTest;
import solvd.laba.ermakovich.hu.service.kafka.operation.CreateDoctorOperation;

/**
 * @author Ermakovich Kseniya
 */
@ExtendWith(MockitoExtension.class)
final class CreateDoctorOperationTest extends BaseTest {

    @Mock
    ElasticDoctorRepository elasticDoctorRepository;

    @InjectMocks
    CreateDoctorOperation createDoctorOperation;

    @Test
    void reactsOnCreateElasticDoctor() {
        var event = new CreateElasticDoctor(doctor);
        Mockito.doReturn(Mono.just(doctor)).when(elasticDoctorRepository)
                .save(Mockito.any(Doctor.class));
        StepVerifier.create(createDoctorOperation.on(event))
                .verifyComplete();
        Mockito.verify(elasticDoctorRepository, Mockito.times(1))
                .save(Mockito.any(Doctor.class));
    }

}
