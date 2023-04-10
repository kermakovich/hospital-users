package solvd.laba.ermakovich.hu.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import solvd.laba.ermakovich.hu.event.IntegrationEvent;

/**
 * @author Ermakovich Kseniya
 */
@RequiredArgsConstructor
@Component
public class KafkaProducer {

    private final KafkaSender<String, IntegrationEvent> sender;

    public void send(final IntegrationEvent value) {
        sender.send(
                Mono.just(
                        SenderRecord.create("finance",
                                0,
                                System.currentTimeMillis(),
                                value.getEventType(),
                                value,
                                null)
                )
        ).subscribe();
    }

}
