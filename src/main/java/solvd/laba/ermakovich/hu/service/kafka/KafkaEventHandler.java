package solvd.laba.ermakovich.hu.service.kafka;

import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.event.integration.IntegrationEvent;

/**
 * @author Ermakovich Kseniya
 */
public interface KafkaEventHandler {

    Mono<Void> onEventReceived(IntegrationEvent value);

}
