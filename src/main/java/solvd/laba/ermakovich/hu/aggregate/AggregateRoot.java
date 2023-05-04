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
    private String id;
    private String type;
    private AggregateStatus status;

    @Version
    private long version;

    protected AggregateRoot(final String id, final String aggregateType) {
        this.id = id;
        this.type = aggregateType;
    }

    protected AggregateRoot(final String id, final String aggregateType,
                            final AggregateStatus status) {
        this(id, aggregateType);
        this.status = status;
    }

}
