package solvd.laba.ermakovich.hu.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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

}
