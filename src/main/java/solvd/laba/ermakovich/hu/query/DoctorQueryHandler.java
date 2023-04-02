package solvd.laba.ermakovich.hu.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.mongo.DoctorRepository;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class DoctorQueryHandler implements DoctorQueryService {

    private final DoctorRepository doctorRepository;

    @Override
    public Mono<Boolean> isExistByEmail(String email) {
        return doctorRepository.existsByDoctor_Email(email);
    }

    @Override
    public Mono<Boolean> isExistByExternalId(UUID externalId) {
        return doctorRepository.existsByDoctor_ExternalId(externalId);
    }

}
