package solvd.laba.ermakovich.hu.event.kafka;


import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.event.integration.CreateElasticDoctor;
import solvd.laba.ermakovich.hu.event.integration.DeleteElasticDoctor;
import solvd.laba.ermakovich.hu.event.integration.IntegrationEvent;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorService {

    CreateElasticDoctor createElasticDoctor(IntegrationEvent event);

    DeleteElasticDoctor deleteElasticDoctor(IntegrationEvent event);

    Mono<Void> when(IntegrationEvent value);

}
