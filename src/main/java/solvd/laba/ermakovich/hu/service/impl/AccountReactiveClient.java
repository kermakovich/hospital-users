package solvd.laba.ermakovich.hu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.service.AccountClient;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@RequiredArgsConstructor
@Service
public class AccountReactiveClient implements AccountClient {

    private final WebClient.Builder webClient;
    private final String HOSPITAL_FINANCE_URL = "http://HOSPITAL-FINANCE";

    @Override
    public void create(UUID externalId) {
        Mono<ResponseEntity> resp = webClient.build()
                .post()
                .uri(HOSPITAL_FINANCE_URL + "/api/v1/accounts")
                .body(Mono.just(externalId), UUID.class)
                .retrieve()
                .bodyToMono(ResponseEntity.class);
        resp.flatMap(responseEntity -> {
            if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
                throw new RuntimeException("bank account has not created");
            }
            return null;
        });

    }
}
