package solvd.laba.ermakovich.hu.query;

import java.util.UUID;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.aggregate.doctor.DoctorAggregate;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorQueryService {

    Mono<Boolean> isExistByEmail(String email);

    Mono<Boolean> isExistByExternalId(UUID externalId);

    Mono<DoctorAggregate> findByIdOrCreate(String aggregateId);

    Mono<DoctorAggregate> findById(String aggregateId);

}
