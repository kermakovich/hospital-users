package solvd.laba.ermakovich.hu.aggregate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.event.Event;
import solvd.laba.ermakovich.hu.mongo.DoctorRepository;
import solvd.laba.ermakovich.hu.query.DoctorQueryService;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class DoctorAggregateServiceImpl implements DoctorAggregateService {

    private final DoctorRepository doctorRepository;
    private final DoctorQueryService doctorQueryService;

    @Override
    public Mono<DoctorAggregate> apply(Event event) {
        return doctorQueryService.findByIdOrCreate(event.getAggregateId())
                .flatMap(aggregate -> {
                    event.copyTo(aggregate);
                    return doctorRepository.save(aggregate);
                });
    }

}
