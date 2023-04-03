package solvd.laba.ermakovich.hu.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.SuperBuilder;
import solvd.laba.ermakovich.hu.domain.Doctor;

/**
 * @author Ermakovich Kseniya
 */
@Data
@SuperBuilder
@AllArgsConstructor
public class CreateDoctor extends Event {

    public static final String EVENT_TYPE = "CreateDoctor";

    public CreateDoctor(String aggregateId) {
        super(EVENT_TYPE, aggregateId);
    }

    @SneakyThrows
    public CreateDoctor(String aggregateId, Doctor doctor) {
        this(aggregateId);
        this.setPayload(new ObjectMapper().writeValueAsString(doctor));
    }

}
