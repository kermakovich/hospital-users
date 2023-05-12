package solvd.laba.ermakovich.hu.domain.command;

import java.util.UUID;
import lombok.Data;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class DeleteDoctorCommand {

    private UUID externalId;

    public DeleteDoctorCommand(final UUID externalId) {
        this.externalId = externalId;
    }

}
