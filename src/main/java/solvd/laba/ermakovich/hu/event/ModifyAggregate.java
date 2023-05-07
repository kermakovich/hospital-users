package solvd.laba.ermakovich.hu.event;

import solvd.laba.ermakovich.hu.aggregate.AggregateRoot;

/**
 * @author Ermakovich Kseniya
 */
public interface ModifyAggregate {

    void copyTo(AggregateRoot aggregate);

}
