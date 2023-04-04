package solvd.laba.ermakovich.hu.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("events")
public abstract class Event {

    @Id
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String aggregateId;
    private String eventType;
    private long version;

    @Getter(value=AccessLevel.NONE)
    @Setter(value=AccessLevel.NONE)
    private String payload;
    private LocalDateTime timeStamp;

    public abstract String getPayload();

    protected Event(String eventType, String aggregateId) {
        this.id = UUID.randomUUID().toString();
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.timeStamp = LocalDateTime.now();
    }

    protected Event(String eventType) {
        this(eventType, null);
    }

}
