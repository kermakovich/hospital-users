package solvd.laba.ermakovich.hu.event.kafka;

import solvd.laba.ermakovich.hu.event.EventRoot;
import solvd.laba.ermakovich.hu.event.IntegrationEvent;

/**
 * @author Ermakovich Kseniya
 */
public interface CreateAccountService {

    EventRoot succeed(IntegrationEvent event);

    EventRoot failed(IntegrationEvent event);

    void when(IntegrationEvent value);

}
