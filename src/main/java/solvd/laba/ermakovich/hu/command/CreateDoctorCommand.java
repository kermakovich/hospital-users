package solvd.laba.ermakovich.hu.command;

import lombok.Data;
import solvd.laba.ermakovich.hu.domain.Doctor;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class CreateDoctorCommand {

    private String aggregateId;
    private Doctor doctor;

    public CreateDoctorCommand(Doctor doctor) {
        this.aggregateId = UUID.randomUUID().toString();
        this.doctor = doctor;
        this.doctor.setExternalId(UUID.randomUUID());
    }

}
