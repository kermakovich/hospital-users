package solvd.laba.ermakovich.hu.event.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solvd.laba.ermakovich.hu.aggregate.DoctorAggregateService;
import solvd.laba.ermakovich.hu.domain.exception.IllegalOperationException;
import solvd.laba.ermakovich.hu.aggregate.AggregateStatus;
import solvd.laba.ermakovich.hu.event.DoctorEventService;
import solvd.laba.ermakovich.hu.event.EventRoot;
import solvd.laba.ermakovich.hu.event.IntegrationEvent;
import solvd.laba.ermakovich.hu.event.UpdateDoctor;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public final class KafkaCreateAccountHandler
        implements CreateAccountService {

    private final DoctorAggregateService aggregateService;
    private final DoctorEventService eventService;

    @Override
    public EventRoot succeed(final IntegrationEvent integrationEvent) {
        return new UpdateDoctor(
                integrationEvent.getAggregateId(),
                AggregateStatus.APPROVED
        );
    }

    @Override
    public EventRoot failed(final IntegrationEvent integrationEvent) {
        return new UpdateDoctor(integrationEvent.getAggregateId(),
                AggregateStatus.REJECTED);
    }

    @Override
    public void when(final IntegrationEvent value) {
        var event = switch (value.getEventType()) {
            case "createAccountCompleted" -> succeed(value);
            case "createAccountRejected" -> failed(value);
            default -> throw new IllegalOperationException(
                    "Unexpected or wrong event status"
            );
        };
        eventService.create(event);
        aggregateService.apply(event).subscribe();
    }

}
