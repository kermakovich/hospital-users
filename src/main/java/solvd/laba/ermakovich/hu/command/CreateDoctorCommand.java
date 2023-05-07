package solvd.laba.ermakovich.hu.command;

import java.util.UUID;
import lombok.Data;
import solvd.laba.ermakovich.hu.domain.Doctor;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class CreateDoctorCommand {

    private String aggregateId;
    private Doctor doctor;

    public CreateDoctorCommand(final Doctor doctor) {
        this.aggregateId = UUID.randomUUID().toString();
        this.doctor = doctor;
        this.doctor.setExternalId(UUID.randomUUID());
    }

}
