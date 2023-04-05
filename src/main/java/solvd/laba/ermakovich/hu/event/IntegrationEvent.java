package solvd.laba.ermakovich.hu.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@Data
@NoArgsConstructor
public abstract class IntegrationEvent {

    private String id;
    private String eventType;
    private String payload;
    private LocalDateTime timeStamp;

    protected IntegrationEvent(String eventType) {
        this.id = UUID.randomUUID().toString();
        this.eventType = eventType;
        this.timeStamp = LocalDateTime.now();
    }

    public abstract String getPayload();

}