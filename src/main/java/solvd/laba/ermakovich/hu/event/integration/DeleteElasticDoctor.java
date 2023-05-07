package solvd.laba.ermakovich.hu.event.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.Data;
import lombok.SneakyThrows;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class DeleteElasticDoctor extends IntegrationEvent {

    public static final String EVENT_TYPE = "deleteElasticDoctor";
    private UUID externalId;

    public DeleteElasticDoctor() {
        super(EVENT_TYPE);
    }

    public DeleteElasticDoctor(final UUID externalId) {
        this();
        this.externalId = externalId;
    }

    @SneakyThrows
    @Override
    public String getPayload() {
        return new ObjectMapper().writeValueAsString(externalId);
    }

}
