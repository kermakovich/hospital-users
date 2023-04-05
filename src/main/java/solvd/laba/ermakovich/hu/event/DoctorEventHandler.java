package solvd.laba.ermakovich.hu.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.aggregate.DoctorAggregateService;
import solvd.laba.ermakovich.hu.mongo.SaveCustom;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class DoctorEventHandler implements DoctorEventService {

    private final SaveCustom<Event> saveCustom;
    private final DoctorAggregateService doctorAggregateService;

    @Override
    public Mono<Void> when(Event event) {
        return doctorAggregateService.apply(event)
                .flatMap(doctorAggregate -> {
                    saveCustom.save(event).subscribe();
                    return Mono.empty();
                });
    }

}
