package solvd.laba.ermakovich.hu.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import solvd.laba.ermakovich.hu.aggregate.AggregateRoot;

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

    @Version
    private long version;
    private String payload;
    private LocalDateTime timeStamp;

    protected Event(String eventType, String aggregateId) {
        this.id = UUID.randomUUID().toString();
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.timeStamp = LocalDateTime.now();
    }

    public abstract String getPayload();

    public abstract void copyTo(AggregateRoot aggregate);

}
