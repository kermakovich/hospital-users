package solvd.laba.ermakovich.hu.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.service.aggregate.DoctorAggregate;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorRepository extends ReactiveMongoRepository<DoctorAggregate, Long> {

    Mono<Boolean> existsByEmail(String email);

    Mono<Boolean> existsByExternalId(UUID externalId);

}
