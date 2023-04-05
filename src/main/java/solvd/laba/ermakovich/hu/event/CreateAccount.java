package solvd.laba.ermakovich.hu.event;

import lombok.Data;
import lombok.SneakyThrows;

import java.util.UUID;

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
    public CreateAccount(UUID externalId) {
        this();
        this.externalId = externalId;
    }

    @Override
    public String getPayload() {
        return externalId.toString();
    }

}
