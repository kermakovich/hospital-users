package solvd.laba.ermakovich.hu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
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
    private final AccountReactiveClient accountReactiveClient;
    private final DoctorRepository doctorRepository;

    @Override
    @Transactional
    public Mono<Doctor> create(Doctor doctor, UserInfo userInfo) {
        if (!UserRole.DOCTOR.equals(userInfo.getRole())) {
            throw new IllegalOperationException("Doctor has wrong role");
        }
        Mono<UserInfo> info = userInfoService.create(userInfo);
        return info.flatMap( savedInfo -> {
            accountReactiveClient.create(savedInfo.getExternalId());
            doctor.setId(userInfo.getId());
            return doctorRepository.save(doctor);
        });
    }
}
