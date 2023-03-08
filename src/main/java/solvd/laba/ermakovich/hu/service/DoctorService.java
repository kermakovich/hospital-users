package solvd.laba.ermakovich.hu.service;

import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.domain.UserInfo;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorService {

    Doctor create(Doctor doctor, UserInfo userInfo);

}
