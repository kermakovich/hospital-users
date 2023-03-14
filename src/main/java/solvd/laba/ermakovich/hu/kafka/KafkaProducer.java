package solvd.laba.ermakovich.hu.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@RequiredArgsConstructor
@Component
public class KafkaProducer {

    private final KafkaSender<String, UUID> sender;

    public void send(final UUID value) {
        sender.send(
                Mono.just(
                        SenderRecord.create("finance",
                                0,
                                System.currentTimeMillis(),
                                "uuid",
                                value,
                                null)
                )
        ).subscribe();
    }
}
