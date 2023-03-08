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
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Boolean isExistByExternalId(String uuid);

    Boolean isExistByEmail(String email);

}
