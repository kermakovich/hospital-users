package solvd.laba.ermakovich.hu.repository.mongo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.event.EventRoot;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public final class SaveCustomImpl implements SaveCustom<EventRoot> {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public Mono<EventRoot> save(final EventRoot eventRoot) {
        final String payload = eventRoot.getPayload();
        eventRoot.setPayload(payload);
        return reactiveMongoTemplate.save(eventRoot);
    }

}
