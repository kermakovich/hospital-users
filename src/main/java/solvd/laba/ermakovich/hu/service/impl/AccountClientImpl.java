package solvd.laba.ermakovich.hu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import solvd.laba.ermakovich.hu.service.AccountClient;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@RequiredArgsConstructor
@Service
public class AccountClientImpl implements AccountClient {

    private final RestTemplate restTemplate;

    @Override
    public void create(UUID externalId) {
        HttpEntity<?> request = new HttpEntity<>(externalId);
        ResponseEntity<?> resp = restTemplate.postForEntity("http://HOSPITAL-FINANCE/api/v1/accounts",
                request, Object.class);
        if (resp.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException();
        }
    }
}
