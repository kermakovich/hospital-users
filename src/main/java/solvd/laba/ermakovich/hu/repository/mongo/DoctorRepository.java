package solvd.laba.ermakovich.hu.repository.mongo;

import java.util.UUID;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.aggregate.doctor.DoctorAggregate;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorRepository
        extends ReactiveMongoRepository<DoctorAggregate, String> {

    Mono<DoctorAggregate> findByDoctorExternalId(UUID externalId);

}
