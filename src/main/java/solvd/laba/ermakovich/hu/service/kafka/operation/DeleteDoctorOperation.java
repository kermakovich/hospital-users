package solvd.laba.ermakovich.hu.service.kafka.operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.repository.elastic.ElasticDoctorRepository;
import solvd.laba.ermakovich.hu.domain.event.integration.DeleteElasticDoctor;
import solvd.laba.ermakovich.hu.domain.event.integration.IntegrationEvent;

/**
 * @author Ermakovich Kseniya
 */
@Component("deleteDoctor")
@RequiredArgsConstructor
public class DeleteDoctorOperation implements Operation {

    private final ElasticDoctorRepository elasticDoctorRepository;

    @Override
    public Mono<Void> on(final IntegrationEvent event) {
        return elasticDoctorRepository.deleteByExternalId(
                getCustomEvent(event).getExternalId()
        );
    }

    @Override
    @SneakyThrows
    public DeleteElasticDoctor getCustomEvent(final IntegrationEvent event) {
        return new DeleteElasticDoctor(new ObjectMapper()
                .readValue(event.getPayload(), UUID.class));
    }

}
