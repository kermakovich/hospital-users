package solvd.laba.ermakovich.hu.service.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import solvd.laba.ermakovich.hu.service.event.Event;

/**
 * @author Ermakovich Kseniya
 */
@Data
@NoArgsConstructor
public abstract class AggregateRoot {

    @Id
    protected String id;
    protected String type;
    protected long version;


    protected AggregateRoot(final String id, final String aggregateType) {
        this.id = id;
        this.type = aggregateType;
    }

    public abstract void when(final Event event);

    public void apply(final Event event) {
        when(event);
        this.version++;
        event.setVersion(this.version);
    }

}
