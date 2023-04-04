package solvd.laba.ermakovich.hu.event;

import reactor.core.publisher.Mono;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorEventService {

    Mono<Void> when(Event event);

}
