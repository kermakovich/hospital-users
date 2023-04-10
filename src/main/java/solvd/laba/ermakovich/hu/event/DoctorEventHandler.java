package solvd.laba.ermakovich.hu.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solvd.laba.ermakovich.hu.mongo.SaveCustom;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class DoctorEventHandler implements DoctorEventService {

    private final SaveCustom<EventRoot> saveCustom;

    @Override
    public void create(EventRoot eventRoot) {
         saveCustom.save(eventRoot).subscribe();
    }

}
