package solvd.laba.ermakovich.hu.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solvd.laba.ermakovich.hu.domain.event.EventRoot;
import solvd.laba.ermakovich.hu.repository.mongo.SaveCustom;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public final class DoctorEventHandler implements DoctorEventService {

    private final SaveCustom<EventRoot> saveCustom;

    @Override
    public void create(final EventRoot eventRoot) {
        saveCustom.save(eventRoot).subscribe();
    }

}
