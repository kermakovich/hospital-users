package solvd.laba.ermakovich.hu.domain.event;

import solvd.laba.ermakovich.hu.domain.aggregate.AggregateRoot;

/**
 * @author Ermakovich Kseniya
 */
public interface ModifyAggregate {

    void copyTo(AggregateRoot aggregate);

}
