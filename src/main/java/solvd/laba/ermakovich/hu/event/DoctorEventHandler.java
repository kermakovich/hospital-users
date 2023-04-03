package solvd.laba.ermakovich.hu.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solvd.laba.ermakovich.hu.aggregate.AggregateService;
import solvd.laba.ermakovich.hu.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.mongo.EventRepository;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class DoctorEventHandler implements DoctorEventService {

    private final EventRepository eventRepository;
    private final AggregateService aggregateService;

    @Override
    @Transactional
    public void when(Event event) {
        DoctorAggregate doctor = new DoctorAggregate(event.getAggregateId());
        aggregateService.apply(doctor, event);
        eventRepository.save(event).subscribe();
    }

}
