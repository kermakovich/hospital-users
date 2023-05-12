package solvd.laba.ermakovich.hu.service.aggregate;

import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.domain.event.EventRoot;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorAggregateService {

    Mono<DoctorAggregate> create(EventRoot eventRoot);

    Mono<Void> delete(EventRoot eventRoot);


}
