package solvd.laba.ermakovich.hu.kafka.consumer;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.KafkaReceiver;
import solvd.laba.ermakovich.hu.domain.event.integration.IntegrationEvent;
import solvd.laba.ermakovich.hu.service.kafka.KafkaEventHandler;

/**
 * @author Ermakovich Kseniya
 */
@Slf4j
@RequiredArgsConstructor
@Component
public final class KafkaConsumer {

    private final KafkaReceiver<String, IntegrationEvent> receiver;
    private final KafkaEventHandler kafkaEventHandler;

    @PostConstruct
    public void fetch() {
        receiver.receive()
                .subscribe(r -> {
                    log.info("kafka consumer received message: {}", r.value());
                    kafkaEventHandler.onEventReceived(r.value())
                            .subscribe();
                    r.receiverOffset().acknowledge();
                });
    }

}
