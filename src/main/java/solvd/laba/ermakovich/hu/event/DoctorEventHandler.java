package solvd.laba.ermakovich.hu.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import solvd.laba.ermakovich.hu.aggregate.DoctorAggregate;
import solvd.laba.ermakovich.hu.mongo.DoctorRepository;
import solvd.laba.ermakovich.hu.mongo.EventRepository;

/**
 * @author Ermakovich Kseniya
 */
@Component
@RequiredArgsConstructor
public class DoctorEventHandler implements DoctorEventService {

    private final DoctorRepository doctorRepository;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public void on(Event event) {
        eventRepository.save(event).subscribe();
        DoctorAggregate doctor = new DoctorAggregate(event.getAggregateId());
        doctor.apply(event);
        doctorRepository.save(doctor).subscribe();
    }

}
