package solvd.laba.ermakovich.hu.aggregate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.event.Event;
import solvd.laba.ermakovich.hu.event.EventRoot;
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
    public Mono<DoctorAggregate> apply(final EventRoot eventRoot) {
        return doctorQueryService.findById(eventRoot.getAggregateId())
                .flatMap(aggregate -> {
                    ((Event) eventRoot).copyTo(aggregate);
                    return doctorRepository.save(aggregate);
                });
    }

    @Override
    public Mono<DoctorAggregate> applyCreateOperation(
            final EventRoot eventRoot
    ) {
        return doctorQueryService.findByIdOrCreate(
                eventRoot.getAggregateId()
                )
                .flatMap(aggregate -> {
                    ((Event) eventRoot).copyTo(aggregate);
                    return doctorRepository.save(aggregate);
                });
    }

}
