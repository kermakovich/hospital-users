package solvd.laba.ermakovich.hu.query;

import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorQueryService {

    Mono<Boolean> isExistByEmail(String email);

    Mono<Boolean> isExistByExternalId(UUID externalId);

}
