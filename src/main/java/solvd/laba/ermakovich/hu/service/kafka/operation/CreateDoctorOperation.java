package solvd.laba.ermakovich.hu.service.kafka.operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.ElasticDoctor;
import solvd.laba.ermakovich.hu.domain.event.integration.CreateElasticDoctor;
import solvd.laba.ermakovich.hu.domain.event.integration.IntegrationEvent;
import solvd.laba.ermakovich.hu.repository.elastic.ElasticDoctorRepository;

/**
 * @author Ermakovich Kseniya
 */
@Component("createDoctor")
@RequiredArgsConstructor
public class CreateDoctorOperation implements Operation {

    private final ElasticDoctorRepository elasticDoctorRepository;

    @Override
    public Mono<Void> on(final IntegrationEvent event) {
        return elasticDoctorRepository.save(getCustomEvent(event).getDoctor())
                .then();
    }

    @Override
    @SneakyThrows
    public CreateElasticDoctor getCustomEvent(final IntegrationEvent event) {
        return new CreateElasticDoctor(new ObjectMapper()
                .readValue(event.getPayload(), ElasticDoctor.class));
    }

}
