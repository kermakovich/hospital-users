package solvd.laba.ermakovich.hu.service.query;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.aggregate.AggregateStatus;
import solvd.laba.ermakovich.hu.domain.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.repository.elastic.ElasticDoctorRepository;
import solvd.laba.ermakovich.hu.repository.mongo.DoctorRepository;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public final class DoctorQueryHandler implements DoctorQueryService {

    private final DoctorRepository doctorRepository;
    private final ElasticDoctorRepository elasticDoctorRepository;

    @Override
    public Mono<Boolean> isExistByEmail(final String email) {
        return elasticDoctorRepository.existsByEmail(email);
    }

    @Override
    public Mono<DoctorAggregate> findByExternalId(final UUID externalId) {
        return doctorRepository.findByDoctorExternalId(externalId);
    }

    @Override
    public Mono<DoctorAggregate> findByIdOrCreate(final String aggregateId) {
        return doctorRepository.findById(aggregateId)
                .switchIfEmpty(
                        Mono.just(
                                new DoctorAggregate(
                                        aggregateId,
                                        AggregateStatus.APPROVED
                                )
                        )
                );
    }

    @Override
    public Flux<Doctor> findAllBySurname(final String surname) {
        return elasticDoctorRepository.findAllBySurname(surname);
    }

}
