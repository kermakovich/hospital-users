package solvd.laba.ermakovich.hu.event;

import java.util.UUID;
import lombok.Data;
import lombok.SneakyThrows;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class CreateAccount extends IntegrationEvent {

    public static final String EVENT_TYPE = "CreateAccount";
    private UUID externalId;

    public CreateAccount() {
        super(EVENT_TYPE);
    }

    @SneakyThrows
    public CreateAccount(UUID externalId, String aggregateId) {
        this();
        this.externalId = externalId;
        this.setAggregateId(aggregateId);
    }

    @SneakyThrows
    @Override
    public String getPayload() {
        return externalId.toString();
    }

}

