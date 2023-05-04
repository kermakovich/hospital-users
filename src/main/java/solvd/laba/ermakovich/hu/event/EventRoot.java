package solvd.laba.ermakovich.hu.event;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Ermakovich Kseniya
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("events")
@SuperBuilder
public abstract class EventRoot {

    @Id
    private String id;
    private String aggregateId;
    private String eventType;

    @Version
    private long version;
    private String payload;
    private LocalDateTime timeStamp;

    protected EventRoot(final String eventType, final String aggregateId) {
        this.id = UUID.randomUUID().toString();
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.timeStamp = LocalDateTime.now();
    }

}
