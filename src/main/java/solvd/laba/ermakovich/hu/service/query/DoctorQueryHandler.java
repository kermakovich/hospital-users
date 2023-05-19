package solvd.laba.ermakovich.hu.service.query;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.DoctorSearchCriteria;
import solvd.laba.ermakovich.hu.domain.aggregate.AggregateStatus;
import solvd.laba.ermakovich.hu.domain.aggregate.doctor.DoctorAggregate;
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
        return doctorRepository.existsByDoctorEmail(email);
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
    public Flux<DoctorAggregate> findAllByCriteria(
            final DoctorSearchCriteria criteria, final Pageable pageable
    ) {
       return elasticDoctorRepository.findAllByCriteria(criteria, pageable)
                .flatMap(elasticDoctor ->
                        doctorRepository.findByDoctorExternalId(
                                elasticDoctor.getExternalId()
                        )
                );
    }

}
