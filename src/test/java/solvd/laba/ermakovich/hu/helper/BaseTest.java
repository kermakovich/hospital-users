package solvd.laba.ermakovich.hu.helper;

import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import solvd.laba.ermakovich.hu.domain.Department;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.domain.Specialization;
import solvd.laba.ermakovich.hu.domain.UserRole;
import solvd.laba.ermakovich.hu.domain.aggregate.AggregateStatus;
import solvd.laba.ermakovich.hu.domain.aggregate.doctor.DoctorAggregate;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
public abstract class BaseTest {

    protected static Doctor doctor;
    protected static DoctorAggregate doctorAggregate;
    protected static DoctorAggregate doctorAggregateWithoutDoctor;

    @BeforeAll
    static void doctorSetup() {
        doctor = new Doctor();
        doctor.setExternalId(UUID.fromString("688e40eb-3209-4a2e-83cc-6a178b1806ab"));
        doctor.setName("alex");
        doctor.setPassword("$2a$10$S8NVlLc9gdQ6Vjn1hzHru.md0wp6Z4en6WOe1se790aZMnjzfdPpm");
        doctor.setId("74e5ed85-c727-4441-8862-166bb4d5f07e");
        doctor.setCabinet(102);
        doctor.setSpecialization(Specialization.THERAPIST);
        doctor.setBirthday(LocalDate.of(1999, 2, 2));
        doctor.setEmail("pomidorov@gmail.com");
        doctor.setFatherhood("sergeevich");
        doctor.setRole(UserRole.DOCTOR);
        doctor.setSurname("pomidorov");
        doctor.setDepartment(Department.THERAPEUTIC);
    }

    @BeforeAll
    static void doctorAggregateSetup() {
        doctorAggregate = new DoctorAggregate(
                UUID.randomUUID().toString(),
                AggregateStatus.APPROVED
        );
        doctorAggregate.setDoctor(doctor);
    }

    @BeforeAll
    static void doctorAggregateWithoutDoctorSetup() {
        doctorAggregateWithoutDoctor = new DoctorAggregate(
                UUID.randomUUID().toString(),
                AggregateStatus.APPROVED
        );
    }

}
