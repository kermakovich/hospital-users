package solvd.laba.ermakovich.hu.service.query;

import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.domain.Doctor;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorQueryService {

    Mono<Boolean> isExistByEmail(String email);

    Mono<DoctorAggregate> findByIdOrCreate(String aggregateId);

    Flux<Doctor> findAllBySurname(String surname);

    Mono<DoctorAggregate> findByExternalId(UUID externalId);

}
