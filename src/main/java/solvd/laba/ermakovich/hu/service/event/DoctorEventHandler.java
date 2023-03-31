package solvd.laba.ermakovich.hu.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import solvd.laba.ermakovich.hu.service.aggregate.DoctorAggregate;
import solvd.laba.ermakovich.hu.repository.DoctorRepository;
import solvd.laba.ermakovich.hu.repository.EventRepository;

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
    public void on(CreateDoctorEvent event) {
        eventRepository.save(event).subscribe();

        DoctorAggregate doctor = new DoctorAggregate(event.getAggregateId());
        doctor.apply(event);

        doctorRepository.save(doctor).subscribe();
    }

}
