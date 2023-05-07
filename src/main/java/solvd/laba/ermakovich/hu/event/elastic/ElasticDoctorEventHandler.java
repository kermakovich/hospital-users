package solvd.laba.ermakovich.hu.event.elastic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.elastic.ElasticDoctorRepository;
import solvd.laba.ermakovich.hu.event.integration.CreateElasticDoctor;
import solvd.laba.ermakovich.hu.event.integration.DeleteElasticDoctor;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class ElasticDoctorEventHandler
        implements ElasticDoctorEventService {

    private final ElasticDoctorRepository elasticDoctorRepository;

    @Override
    public Mono<Doctor> create(final CreateElasticDoctor createElasticDoctor) {
        return elasticDoctorRepository.save(createElasticDoctor.getDoctor());
    }

    @Override
    public Mono<Void> delete(final DeleteElasticDoctor event) {
        return elasticDoctorRepository.deleteByExternalId(
                event.getExternalId()
        );
    }

}
