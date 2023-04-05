package solvd.laba.ermakovich.hu.mongo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.event.Event;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class SaveCustomImpl implements SaveCustom<Event> {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public Mono<Event> save(Event event) {
        final String payload = event.getPayload();
        event.setPayload(payload);
        reactiveMongoTemplate.save(event).subscribe();
        return Mono.empty();
    }

}