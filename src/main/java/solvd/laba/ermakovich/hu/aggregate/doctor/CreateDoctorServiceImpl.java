package solvd.laba.ermakovich.hu.aggregate.doctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.event.CreateDoctor;
import solvd.laba.ermakovich.hu.mongo.DoctorRepository;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class CreateDoctorServiceImpl implements CreateDoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    @SneakyThrows
    public void on(DoctorAggregate aggregate, final CreateDoctor event) {
        aggregate.setDoctor(new ObjectMapper().readValue(event.getPayload(), Doctor.class));
        doctorRepository.save(aggregate).subscribe();
    }

}
