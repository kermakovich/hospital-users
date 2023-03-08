package solvd.laba.ermakovich.hu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.domain.UserInfo;
import solvd.laba.ermakovich.hu.domain.exception.ResourceAlreadyExistsException;
import solvd.laba.ermakovich.hu.repository.UserInfoRepository;
import solvd.laba.ermakovich.hu.service.UserInfoService;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public UserInfo create(UserInfo userInfo) {
        if (userRepository.isExistByEmail(userInfo.getEmail())) {
            throw new ResourceAlreadyExistsException(" User with this email: " + userInfo.getEmail() + " already exist");
        }
        userInfo.setPassword(hashPassword(userInfo.getPassword()));
        userInfo.setExternalId(UUID.randomUUID());
        userRepository.save(userInfo);
        return userInfo;
    }

    @Override
    public Boolean isExistByExternalId(UUID externalId) {
        return userRepository.isExistByExternalId(externalId.toString());
    }
    private String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

}
