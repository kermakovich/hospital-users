package solvd.laba.ermakovich.hu.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import solvd.laba.ermakovich.hu.domain.Doctor;

/**
 * @author Ermakovich Kseniya
 */
@Data
@AllArgsConstructor
public class CreateDoctor extends Event {

    public static final String EVENT_TYPE = "CreateDoctor";
    private Doctor doctor;

    @SneakyThrows
    @Override
    public String getPayload() {
        return new ObjectMapper().writeValueAsString(doctor);
    }

    public CreateDoctor(String aggregateId) {
        super(EVENT_TYPE, aggregateId);
    }

    public CreateDoctor(String aggregateId, Doctor doctor) {
        this(aggregateId);
        this.doctor = doctor;
    }

}
