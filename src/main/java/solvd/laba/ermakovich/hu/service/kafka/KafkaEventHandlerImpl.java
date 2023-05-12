package solvd.laba.ermakovich.hu.service.kafka;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.event.integration.IntegrationEvent;
import solvd.laba.ermakovich.hu.service.kafka.operation.Operation;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class KafkaEventHandlerImpl implements KafkaEventHandler {

    private final Map<String, Operation> operations;

    public Mono<Void> onEventReceived(final IntegrationEvent value) {
        return operations.get(value.getEventType())
                .on(value);
    }

}
