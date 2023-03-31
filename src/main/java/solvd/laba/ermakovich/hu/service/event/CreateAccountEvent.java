package solvd.laba.ermakovich.hu.service.event;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public class CreateAccountEvent extends Event {

    public static final String EVENT_TYPE = "CreateAccount";

    public CreateAccountEvent(UUID externalId) {
        super(EVENT_TYPE);
        this.setPayload(externalId.toString());
    }

}
