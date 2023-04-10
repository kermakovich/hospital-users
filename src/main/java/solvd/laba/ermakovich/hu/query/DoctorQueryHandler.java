package solvd.laba.ermakovich.hu.query;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.domain.exception.ResourceDoesNotExistException;
import solvd.laba.ermakovich.hu.aggregate.AggregateStatus;
import solvd.laba.ermakovich.hu.mongo.DoctorRepository;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class DoctorQueryHandler implements DoctorQueryService {

    private final DoctorRepository doctorRepository;

    @Override
    public Mono<Boolean> isExistByEmail(String email) {
        return doctorRepository.existsByDoctorEmail(email);
    }

    @Override
    public Mono<Boolean> isExistByExternalId(UUID externalId) {
        return doctorRepository.existsByDoctorExternalId(externalId);
    }

    @Override
    public Mono<DoctorAggregate> findByIdOrCreate(String aggregateId) {
        return doctorRepository.findById(aggregateId)
                .switchIfEmpty(
                        Mono.just(new DoctorAggregate(aggregateId, AggregateStatus.PENDING))
                );
    }

    @Override
    public Mono<DoctorAggregate> findById(String aggregateId) {
        return doctorRepository.findById(aggregateId)
                .switchIfEmpty(
                        Mono.error(new ResourceDoesNotExistException("doctor aggregate does not exist"))
                );
    }

}
