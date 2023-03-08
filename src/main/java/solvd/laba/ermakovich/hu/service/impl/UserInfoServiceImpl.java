package solvd.laba.ermakovich.hu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
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
    public Mono<Boolean> isExistByExternalId(UUID externalId) {
        return userRepository.isExistByExternalId(externalId.toString());
    }


    @Override
    @Transactional
    public Mono<UserInfo> create(UserInfo userInfo) {
        return userRepository.isExistByEmail(userInfo.getEmail())
                .flatMap(isExist -> {
                    if (Boolean.TRUE.equals(isExist)) {
                        throw new ResourceAlreadyExistsException(" User with this email: " + userInfo.getEmail() + " already exist");
                    } else {
                        userInfo.setPassword(hashPassword(userInfo.getPassword()));
                        userInfo.setExternalId(UUID.randomUUID());
                        return userRepository.save(userInfo);
                    }
        });
    }

    private String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

}
