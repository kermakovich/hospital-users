package solvd.laba.ermakovich.hu.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

/**
 * @author Ermakovich Kseniya
 */
@Data
@NoArgsConstructor
public abstract class AggregateRoot {

    @Id
    protected String id;
    protected String type;
    protected AggregateStatus status;

    @Version
    protected long version;

    protected AggregateRoot(final String id, final String aggregateType) {
        this.id = id;
        this.type = aggregateType;
    }

    protected AggregateRoot(String id, String aggregateType, AggregateStatus status) {
        this(id, aggregateType);
        this.status = status;
    }

}
