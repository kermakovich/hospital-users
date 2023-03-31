package solvd.laba.ermakovich.hu.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import solvd.laba.ermakovich.hu.service.event.CreateAccountEvent;
import solvd.laba.ermakovich.hu.service.event.Event;

/**
 * @author Ermakovich Kseniya
 */
@RequiredArgsConstructor
@Component
public class KafkaProducer {

    private final KafkaSender<String, Event> sender;

    public void send(final Event value) {
        sender.send(
                Mono.just(
                        SenderRecord.create("finance",
                                0,
                                System.currentTimeMillis(),
                                CreateAccountEvent.EVENT_TYPE,
                                value,
                                null)
                )
        ).subscribe();
    }

}
