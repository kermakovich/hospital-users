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

/**
 * @author Ermakovich Kseniya
 */
@RequiredArgsConstructor
public class ElasticsearchFilterImpl implements ElasticsearchFilter {

    private final ReactiveElasticsearchOperations operations;

    @Override
    public Flux<ElasticDoctor> findAllByCriteria(
            final DoctorSearchCriteria searchCriteria,
            final Pageable pageable
    ) {
        Criteria criteria = new Criteria();
        if (searchCriteria.getDepartment() != null) {
            criteria.and(Criteria.where("department")
                    .is(searchCriteria.getDepartment()));
        }
        if (searchCriteria.getPatientAge() != null) {
            criteria.and(Criteria.where("patientAges")
                            .is(searchCriteria.getPatientAge())
            );
        }
        if (searchCriteria.getSurname() != null) {
            criteria.and(Criteria.where("surname")
                    .is(searchCriteria.getSurname())
            );
        }
        if (searchCriteria.getSpecialization() != null) {
            criteria.and(Criteria.where("specialization")
                            .in(searchCriteria.getSpecialization())
            );
        }
        if (searchCriteria.getExperienceFrom() != null) {
            criteria.and(Criteria.where("experience")
                    .greaterThanEqual(searchCriteria.getExperienceFrom())
            );
        }
        if (searchCriteria.getExperienceTo() != null) {
            criteria.and(Criteria.where("experience")
                    .greaterThanEqual(searchCriteria.getExperienceTo())
            );
        }
        if (searchCriteria.getPricePerHourFrom() != null) {
            criteria.and(Criteria.where("pricePerHour")
                    .is(searchCriteria.getPricePerHourFrom())
            );
        }
        if (searchCriteria.getPricePerHourTo() != null) {
            criteria.and(Criteria.where("pricePerHour")
                    .is(searchCriteria.getPricePerHourTo())
            );
        }
        Query query = new CriteriaQuery(criteria)
                .setPageable(pageable);
        return operations.search(query, ElasticDoctor.class)
                .flatMap(hit -> Mono.just(hit.getContent()));
    }

}
