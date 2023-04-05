package solvd.laba.ermakovich.hu.query;

import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.aggregate.doctor.DoctorAggregate;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorQueryService {

    Mono<Boolean> isExistByEmail(String email);

    Mono<Boolean> isExistByExternalId(UUID externalId);

    Mono<DoctorAggregate> findByIdOrCreate(String aggregateId);

}
