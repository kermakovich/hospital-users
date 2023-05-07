package solvd.laba.ermakovich.hu.event.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.domain.exception.IllegalOperationException;
import solvd.laba.ermakovich.hu.event.elastic.ElasticDoctorEventService;
import solvd.laba.ermakovich.hu.event.integration.CreateElasticDoctor;
import solvd.laba.ermakovich.hu.event.integration.DeleteElasticDoctor;
import solvd.laba.ermakovich.hu.event.integration.IntegrationEvent;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public final class KafkaDoctorHandler implements DoctorService {

    private final ElasticDoctorEventService elasticEventService;


    @Override
    @SneakyThrows
    public CreateElasticDoctor createElasticDoctor(
            final IntegrationEvent event
    ) {
        return new CreateElasticDoctor(new ObjectMapper()
                .readValue(event.getPayload(), Doctor.class));
    }

    @Override
    @SneakyThrows
    public DeleteElasticDoctor deleteElasticDoctor(
            final IntegrationEvent event
    ) {
        return new DeleteElasticDoctor(new ObjectMapper()
                .readValue(event.getPayload(), UUID.class));
    }

    @Override
    public Mono<Void> when(final IntegrationEvent value) {
        return switch (value.getEventType()) {
            case "createElasticDoctor" -> {
                var event = createElasticDoctor(value);
                yield elasticEventService.create(event)
                        .then();
            }
            case "deleteElasticDoctor" -> {
                var event = deleteElasticDoctor(value);
                yield elasticEventService.delete(event)
                        .then();
            }
            default -> throw new IllegalOperationException(
                    "Unexpected or wrong event type"
            );
        };
    }

}
