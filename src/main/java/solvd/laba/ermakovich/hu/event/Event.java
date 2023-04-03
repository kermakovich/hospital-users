package solvd.laba.ermakovich.hu.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
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
@SuperBuilder
@Document("events")
public abstract class Event {

    @Id
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String aggregateId;
    private String eventType;
    private long version;
    private String payload;
    private LocalDateTime timeStamp;

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
