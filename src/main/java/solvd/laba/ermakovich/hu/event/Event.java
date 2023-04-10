package solvd.laba.ermakovich.hu.event;

import solvd.laba.ermakovich.hu.aggregate.AggregateRoot;

/**
 * @author Ermakovich Kseniya
 */
public interface Event {

    void copyTo(AggregateRoot aggregate);

    String getPayload();

}
