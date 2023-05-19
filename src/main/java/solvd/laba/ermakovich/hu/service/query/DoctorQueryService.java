package solvd.laba.ermakovich.hu.service.query;

import java.util.UUID;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.DoctorSearchCriteria;
import solvd.laba.ermakovich.hu.domain.aggregate.doctor.DoctorAggregate;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorQueryService {

    Mono<Boolean> isExistByEmail(String email);

    Mono<DoctorAggregate> findByIdOrCreate(String aggregateId);

    Mono<DoctorAggregate> findByExternalId(UUID externalId);

    Flux<DoctorAggregate> findAllByCriteria(
            DoctorSearchCriteria criteria, Pageable pageable
    );

}
