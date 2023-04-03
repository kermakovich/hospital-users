package solvd.laba.ermakovich.hu.event;

import lombok.SneakyThrows;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public class CreateAccount extends Event {

    public static final String EVENT_TYPE = "CreateAccount";

    public CreateAccount() {
        super(EVENT_TYPE);
    }

    @SneakyThrows
    public CreateAccount(UUID externalId) {
        this();
        this.setPayload(externalId.toString());
    }

}
