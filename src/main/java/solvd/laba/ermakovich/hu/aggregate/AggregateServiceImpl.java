package solvd.laba.ermakovich.hu.aggregate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solvd.laba.ermakovich.hu.aggregate.doctor.CreateDoctorService;
import solvd.laba.ermakovich.hu.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.event.CreateDoctor;
import solvd.laba.ermakovich.hu.event.Event;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class AggregateServiceImpl implements AggregateService {

    private final CreateDoctorService createDoctorService;

    @Override
    public void apply(AggregateRoot aggregateRoot, Event event) {
        aggregateRoot.version++;
        event.setVersion(aggregateRoot.version);
        if (CreateDoctor.EVENT_TYPE.equals(event.getEventType()) &&
                DoctorAggregate.AGGREGATE_TYPE.equals(aggregateRoot.getType())) {
            createDoctorService.on((DoctorAggregate) aggregateRoot, (CreateDoctor) event);
        }
    }

}
