package solvd.laba.ermakovich.hu.service.aggregate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.mongodb.core.mapping.Document;
import solvd.laba.ermakovich.hu.domain.Department;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.domain.Specialization;
import solvd.laba.ermakovich.hu.domain.UserRole;
import solvd.laba.ermakovich.hu.service.event.CreateDoctorEvent;
import solvd.laba.ermakovich.hu.service.event.Event;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@NoArgsConstructor
@Document("doctors")
@Data
public class DoctorAggregate extends AggregateRoot {

    public static final String AGGREGATE_TYPE = "Doctor";

    private Department department;
    private Specialization specialization;
    private Integer cabinet;
    private UUID externalId;
    private String name;
    private String surname;
    private String fatherhood;
    private LocalDate birthday;
    private String email;
    private String password;
    private UserRole role;


    public DoctorAggregate(String id) {
        super(id, AGGREGATE_TYPE);
    }

    @Override
    public void when(Event event) {
        if (event.getEventType().equals(CreateDoctorEvent.EVENT_TYPE)) {
            on((CreateDoctorEvent) event);
        }
    }

    @SneakyThrows
    private void on(final CreateDoctorEvent event) {
        Doctor doctor = new ObjectMapper().readValue(event.getPayload(), Doctor.class);
        department = doctor.getDepartment();
        specialization = doctor.getSpecialization();
        cabinet = doctor.getCabinet();
        externalId = doctor.getExternalId();
        name = doctor.getName();
        surname = doctor.getSurname();
        fatherhood = doctor.getFatherhood();
        birthday = doctor.getBirthday();
        email = doctor.getEmail();
        password = doctor.getPassword();
        role = doctor.getRole();
    }

}
