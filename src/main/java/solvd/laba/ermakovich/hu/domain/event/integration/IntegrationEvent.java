package solvd.laba.ermakovich.hu.domain.event.integration;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ermakovich Kseniya
 */
@Data
@NoArgsConstructor
public class IntegrationEvent {

    private String eventType;
    private String payload;

    protected IntegrationEvent(final String eventType) {
        this.eventType = eventType;
    }

}
