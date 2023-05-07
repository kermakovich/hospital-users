package solvd.laba.ermakovich.hu.event.elastic;

import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.event.integration.CreateElasticDoctor;
import solvd.laba.ermakovich.hu.event.integration.DeleteElasticDoctor;

/**
 * @author Ermakovich Kseniya
 */
public interface ElasticDoctorEventService {

    Mono<Doctor> create(CreateElasticDoctor createElasticDoctor);

    Mono<Void> delete(DeleteElasticDoctor event);

}
