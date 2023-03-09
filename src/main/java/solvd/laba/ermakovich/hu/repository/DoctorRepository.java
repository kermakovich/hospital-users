package solvd.laba.ermakovich.hu.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import solvd.laba.ermakovich.hu.domain.Doctor;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorRepository extends R2dbcRepository<Doctor, Long> {

}
