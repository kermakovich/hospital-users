package solvd.laba.ermakovich.hu.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import solvd.laba.ermakovich.hu.event.integration.IntegrationEvent;

/**
 * @author Ermakovich Kseniya
 */
@RequiredArgsConstructor
@Component
public final class KafkaProducer {

    private final KafkaSender<String, IntegrationEvent> sender;

    public void synchronizeElastic(final IntegrationEvent value) {
        sender.send(
                Mono.just(
                        SenderRecord.create("elasticsearch",
                                0,
                                System.currentTimeMillis(),
                                value.getEventType(),
                                value,
                                null)
                )
        ).subscribe();
    }

}
