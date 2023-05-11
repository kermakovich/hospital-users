package solvd.laba.ermakovich.hu.domain.event.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.Data;
import lombok.SneakyThrows;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class DeleteElasticDoctor extends IntegrationEvent {

    public static final String OPERATION_TYPE = "deleteDoctor";
    private UUID externalId;

    public DeleteElasticDoctor() {
        super(OPERATION_TYPE);
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
