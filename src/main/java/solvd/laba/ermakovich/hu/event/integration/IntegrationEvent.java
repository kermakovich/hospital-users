package solvd.laba.ermakovich.hu.event.integration;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Ermakovich Kseniya
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class IntegrationEvent {

    private String id;
    private String eventType;
    private String payload;
    private LocalDateTime timeStamp;

    protected IntegrationEvent(final String eventType) {
        this.id = UUID.randomUUID().toString();
        this.eventType = eventType;
        this.timeStamp = LocalDateTime.now();
    }

}
