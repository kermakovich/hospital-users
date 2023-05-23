package solvd.laba.ermakovich.hu.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import solvd.laba.ermakovich.hu.domain.ElasticDoctor;
import solvd.laba.ermakovich.hu.helper.BaseTest;
import solvd.laba.ermakovich.hu.helper.ElasticsearchBaseIT;
import solvd.laba.ermakovich.hu.repository.elastic.ElasticDoctorRepository;


/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
final class ElasticsearchFilterImplIT extends ElasticsearchBaseIT {

    private static final Pageable DEFAULT_PAGE_REQUEST = PageRequest.of(0, 20);

    @Autowired
    private ElasticDoctorRepository repository;

    @Test
    void getsAllDoctors() {
        Mono<ElasticDoctor> mono = repository.save(BaseTest.elasticDoctor);
        StepVerifier.create(mono)
                .expectNext(BaseTest.elasticDoctor)
                .expectNextCount(0)
                .verifyComplete();
        Flux<ElasticDoctor> doctorFlux = repository
                .findAllByCriteria(BaseTest.emptyDoctorSearchCriteria,
                        DEFAULT_PAGE_REQUEST
                );
        StepVerifier.create(doctorFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

}
