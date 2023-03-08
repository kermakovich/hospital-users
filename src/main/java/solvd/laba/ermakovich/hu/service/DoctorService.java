package solvd.laba.ermakovich.hu.service;

import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.domain.UserInfo;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorService {

    Mono<Doctor> create(Doctor doctor, UserInfo userInfo);

}
