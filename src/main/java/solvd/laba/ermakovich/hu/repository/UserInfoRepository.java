package solvd.laba.ermakovich.hu.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.UserInfo;

/**
 * @author Ermakovich Kseniya
 */
public interface UserInfoRepository extends R2dbcRepository<UserInfo, Long> {

    @Query("SELECT EXISTS(SELECT 1 FROM user_info ui WHERE ui.external_id::text = :uuid)")
    Mono<Boolean> isExistByExternalId(@Param("uuid") String uuid);

    @Query("SELECT EXISTS(SELECT 1 FROM user_info ui WHERE ui.email = :email)")
    Mono<Boolean> isExistByEmail(@Param("email") String email);

}
