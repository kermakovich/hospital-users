package solvd.laba.ermakovich.hu.domain.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.SuperBuilder;

/**
 * @author Ermakovich Kseniya
 */
@Data
@AllArgsConstructor
@SuperBuilder
public final class DeleteDoctor extends EventRoot implements Event {

    public static final String EVENT_TYPE = "DeleteDoctor";

    public DeleteDoctor(final String aggregateId) {
        super(EVENT_TYPE, aggregateId);
    }

    @SneakyThrows
    @Override
    public String getPayload() {
        return new ObjectMapper().writeValueAsString(getAggregateId());
    }

}
