package solvd.laba.ermakovich.hu.repository.elastic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.DoctorSearchCriteria;
import solvd.laba.ermakovich.hu.domain.ElasticDoctor;
import solvd.laba.ermakovich.hu.repository.elastic.criteria.CriteriaBuilder;

/**
 * @author Ermakovich Kseniya
 */
@RequiredArgsConstructor
public class ElasticsearchFilterImpl implements ElasticsearchFilter {

    private final ReactiveElasticsearchOperations operations;
    private final CriteriaBuilder criteriaBuilder;

    @Override
    public Flux<ElasticDoctor> findAllByCriteria(
            final DoctorSearchCriteria searchCriteria,
            final Pageable pageable
    ) {
        Criteria criteria = criteriaBuilder.apply(searchCriteria);
        Query query = new CriteriaQuery(criteria)
                .setPageable(pageable);
        return operations.search(query, ElasticDoctor.class)
                .flatMap(hit -> Mono.just(hit.getContent()));
    }

}
