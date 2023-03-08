package solvd.laba.ermakovich.hu.service;

import solvd.laba.ermakovich.hu.domain.UserInfo;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public interface UserInfoService {

    UserInfo create(UserInfo userInfo);

    Boolean isExistByExternalId(UUID externalId);
}
