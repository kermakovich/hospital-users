package solvd.laba.ermakovich.hu.aggregate;

import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.event.EventRoot;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorAggregateService {

    Mono<DoctorAggregate> apply(EventRoot eventRoot);

    Mono<DoctorAggregate> applyCreateOperation(EventRoot eventRoot);


}
