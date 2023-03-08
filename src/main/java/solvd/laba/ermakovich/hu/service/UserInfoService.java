package solvd.laba.ermakovich.hu.service;


import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.UserInfo;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public interface UserInfoService {

    Mono<Boolean> isExistByExternalId(UUID externalId);

    Mono<UserInfo> create(UserInfo userInfo);

}
