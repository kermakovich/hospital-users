package solvd.laba.ermakovich.hu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.domain.UserInfo;
import solvd.laba.ermakovich.hu.domain.UserRole;
import solvd.laba.ermakovich.hu.domain.exception.IllegalOperationException;
import solvd.laba.ermakovich.hu.repository.DoctorRepository;
import solvd.laba.ermakovich.hu.service.DoctorService;
import solvd.laba.ermakovich.hu.service.UserInfoService;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final UserInfoService userInfoService;
    private final AccountClientImpl accountClient;
    private final DoctorRepository doctorRepository;

    @Override
    @Transactional
    public Doctor create(Doctor doctor, UserInfo userInfo) {
        if (!UserRole.DOCTOR.equals(userInfo.getRole())) {
            throw new IllegalOperationException("Doctor has wrong role");
        }
        UserInfo info = userInfoService.create(userInfo);
        accountClient.create(info.getExternalId());
        doctor.setId(doctor.getId());
        doctorRepository.save(doctor);
        return doctor;
    }
}
