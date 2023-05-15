package solvd.laba.ermakovich.hu.domain.event.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import solvd.laba.ermakovich.hu.domain.ElasticDoctor;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class CreateElasticDoctor extends IntegrationEvent {

    public static final String EVENT_TYPE = "createDoctor";
    private ElasticDoctor doctor;

    public CreateElasticDoctor() {
        super(EVENT_TYPE);
    }

    public CreateElasticDoctor(final ElasticDoctor doctor) {
        this();
        this.doctor = doctor;
    }

    @SneakyThrows
    @Override
    public String getPayload() {
        return new ObjectMapper().writeValueAsString(doctor);
    }

}
