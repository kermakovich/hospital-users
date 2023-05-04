package solvd.laba.ermakovich.hu.kafka.consumer;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.KafkaReceiver;
import solvd.laba.ermakovich.hu.event.IntegrationEvent;
import solvd.laba.ermakovich.hu.event.kafka.CreateAccountService;

/**
 * @author Ermakovich Kseniya
 */
@Slf4j
@RequiredArgsConstructor
@Component
public final class KafkaConsumer {

    private final KafkaReceiver<String, IntegrationEvent> receiver;
    private final CreateAccountService createAccountService;

    @PostConstruct
    public void fetch() {
        receiver.receive()
                .subscribe(r -> {
                    log.info("kafka consumer received message: {}", r.value());
                    createAccountService.when(r.value());
                    r.receiverOffset().acknowledge();
                });
    }

}
