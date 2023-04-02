package solvd.laba.ermakovich.hu.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.aggregate.DoctorAggregate;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorRepository extends ReactiveMongoRepository<DoctorAggregate, String> {

    Mono<Boolean> existsByDoctor_Email(String email);

    Mono<Boolean> existsByDoctor_ExternalId(UUID externalId);

}
