package solvd.laba.ermakovich.hu.service.aggregate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.service.query.DoctorQueryService;
import solvd.laba.ermakovich.hu.domain.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.domain.event.EventRoot;
import solvd.laba.ermakovich.hu.domain.event.ModifyAggregate;
import solvd.laba.ermakovich.hu.repository.mongo.DoctorRepository;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public final class DoctorAggregateServiceImpl
        implements DoctorAggregateService {

    private final DoctorRepository doctorRepository;
    private final DoctorQueryService doctorQueryService;

    @Override
    public Mono<DoctorAggregate> create(
            final EventRoot eventRoot
    ) {
        return doctorQueryService.findByIdOrCreate(
                        eventRoot.getAggregateId()
                )
                .flatMap(aggregate -> {
                    ((ModifyAggregate) eventRoot).copyTo(aggregate);
                    return doctorRepository.save(aggregate);
                });
    }

    @Override
    public Mono<Void> delete(final EventRoot eventRoot) {
        return doctorRepository.deleteById(eventRoot.getAggregateId());
    }

}
