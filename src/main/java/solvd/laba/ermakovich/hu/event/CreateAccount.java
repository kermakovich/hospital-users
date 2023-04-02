package solvd.laba.ermakovich.hu.event;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public class CreateAccount extends Event {

    public static final String EVENT_TYPE = "CreateAccount";

    public CreateAccount(UUID externalId) {
        super(EVENT_TYPE);
        this.setPayload(externalId.toString());
    }

}
