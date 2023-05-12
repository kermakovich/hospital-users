package solvd.laba.ermakovich.hu.service.kafka.operation;

import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.event.integration.IntegrationEvent;

/**
 * @author Ermakovich Kseniya
 */
public interface Operation {

    Mono<Void> on(IntegrationEvent event);

    <T extends IntegrationEvent> T getCustomEvent(IntegrationEvent event);

}
