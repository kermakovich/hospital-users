package solvd.laba.ermakovich.hu.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import solvd.laba.ermakovich.hu.domain.Doctor;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorRepository extends ReactiveCrudRepository<Doctor, Long> {

}
