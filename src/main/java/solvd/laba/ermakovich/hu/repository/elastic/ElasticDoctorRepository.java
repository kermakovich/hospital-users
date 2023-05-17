package solvd.laba.ermakovich.hu.repository.elastic;

import java.util.UUID;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.ElasticDoctor;

/**
 * @author Ermakovich Kseniya
 */
public interface ElasticDoctorRepository
        extends ReactiveElasticsearchRepository<ElasticDoctor, String>,
                ElasticsearchFilter {

    Mono<Void> deleteByExternalId(UUID externalId);

}
