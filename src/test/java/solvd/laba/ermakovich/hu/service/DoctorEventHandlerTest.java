package solvd.laba.ermakovich.hu.service;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.event.CreateDoctor;
import solvd.laba.ermakovich.hu.service.event.DoctorEventHandler;
import solvd.laba.ermakovich.hu.domain.event.EventRoot;
import solvd.laba.ermakovich.hu.repository.mongo.SaveCustom;


/**
 * @author Ermakovich Kseniya
 */
@ExtendWith(MockitoExtension.class)
final class DoctorEventHandlerTest {

    @Mock
    SaveCustom<EventRoot> saveCustom;

    @InjectMocks
    DoctorEventHandler doctorEventHandler;

    @Test
    void verifiesCreateDoctor() {
        var event = new CreateDoctor(UUID.randomUUID().toString());
        Mockito.doReturn(Mono.just(event))
                .when(saveCustom)
                .save(Mockito.any(EventRoot.class));
        doctorEventHandler.create(event);
        Mockito.verify(saveCustom, Mockito.times(1))
                .save(Mockito.any(EventRoot.class));
    }

}
