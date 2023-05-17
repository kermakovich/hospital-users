package solvd.laba.ermakovich.hu.repository.elastic;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import solvd.laba.ermakovich.hu.domain.DoctorSearchCriteria;
import solvd.laba.ermakovich.hu.domain.ElasticDoctor;

/**
 * @author Ermakovich Kseniya
 */
public interface ElasticsearchFilter {

    Flux<ElasticDoctor> findAllByCriteria(DoctorSearchCriteria criteria,
                                          Pageable pageable);

}
