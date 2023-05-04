package solvd.laba.ermakovich.hu.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Transient;
import solvd.laba.ermakovich.hu.aggregate.AggregateRoot;
import solvd.laba.ermakovich.hu.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.domain.Doctor;

/**
 * @author Ermakovich Kseniya
 */
@Data
@AllArgsConstructor
@SuperBuilder
public final class CreateDoctor extends EventRoot implements Event {

    public static final String EVENT_TYPE = "CreateDoctor";

    @Transient
    private Doctor doctor;

    public CreateDoctor(final String aggregateId) {
        super(EVENT_TYPE, aggregateId);
    }

    public CreateDoctor(final String aggregateId, final Doctor doctor) {
        this(aggregateId);
        this.doctor = doctor;
    }

    @SneakyThrows
    @Override
    public String getPayload() {
        return new ObjectMapper().writeValueAsString(doctor);
    }

    @SneakyThrows
    @Override
    public void copyTo(final AggregateRoot aggregate) {
        ((DoctorAggregate) aggregate).setDoctor(new ObjectMapper()
                .readValue(getPayload(), Doctor.class));
    }

}
