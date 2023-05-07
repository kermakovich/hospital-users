package solvd.laba.ermakovich.hu.aggregate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.event.EventRoot;
import solvd.laba.ermakovich.hu.event.ModifyAggregate;
import solvd.laba.ermakovich.hu.mongo.DoctorRepository;
import solvd.laba.ermakovich.hu.query.DoctorQueryService;

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
