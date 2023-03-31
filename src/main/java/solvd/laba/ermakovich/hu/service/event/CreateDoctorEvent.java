package solvd.laba.ermakovich.hu.service.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import solvd.laba.ermakovich.hu.domain.Doctor;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class CreateDoctorEvent extends Event {

    public static final String EVENT_TYPE = "CreateDoctor";

    @SneakyThrows
    public CreateDoctorEvent(String aggregateId, Doctor doctor) {
        super(EVENT_TYPE, aggregateId);
        this.setPayload(new ObjectMapper().writeValueAsString(doctor));
    }

}
