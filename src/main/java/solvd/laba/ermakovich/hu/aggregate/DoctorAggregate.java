package solvd.laba.ermakovich.hu.aggregate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.mongodb.core.mapping.Document;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.event.CreateDoctor;
import solvd.laba.ermakovich.hu.event.Event;

/**
 * @author Ermakovich Kseniya
 */
@NoArgsConstructor
@Document("doctors")
@Data
public class DoctorAggregate extends AggregateRoot {

    public static final String AGGREGATE_TYPE = "Doctor";
    private Doctor doctor;

    public DoctorAggregate(String id) {
        super(id, AGGREGATE_TYPE);
    }

    @Override
    public void when(Event event) {
        if (event.getEventType().equals(CreateDoctor.EVENT_TYPE)) {
            on((CreateDoctor) event);
        }
    }

    @SneakyThrows
    private void on(final CreateDoctor event) {
        this.doctor = new ObjectMapper().readValue(event.getPayload(), Doctor.class);
    }

}
