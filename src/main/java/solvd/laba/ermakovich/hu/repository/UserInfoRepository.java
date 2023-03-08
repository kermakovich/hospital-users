package solvd.laba.ermakovich.hu.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.UserInfo;

/**
 * @author Ermakovich Kseniya
 */
@Repository
public interface UserInfoRepository extends ReactiveCrudRepository<UserInfo, Long> {

    @Query("SELECT EXISTS(SELECT 1 FROM user_info ui WHERE ui.external_id::text = :uuid)")
    Mono<Boolean> isExistByExternalId(@Param("uuid") String uuid);

    @Query("SELECT EXISTS(SELECT 1 FROM user_info ui WHERE ui.email = :email)")
    Mono<Boolean> isExistByEmail(@Param("email") String email);

}
