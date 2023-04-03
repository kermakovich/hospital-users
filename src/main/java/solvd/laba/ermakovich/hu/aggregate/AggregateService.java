package solvd.laba.ermakovich.hu.aggregate;

import solvd.laba.ermakovich.hu.event.Event;

/**
 * @author Ermakovich Kseniya
 */
public interface AggregateService {

    void apply(AggregateRoot aggregateRoot, Event event);

}
