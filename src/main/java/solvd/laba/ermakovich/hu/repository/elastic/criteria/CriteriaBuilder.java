package solvd.laba.ermakovich.hu.repository.elastic.criteria;

import org.springframework.data.elasticsearch.core.query.Criteria;
import solvd.laba.ermakovich.hu.domain.DoctorSearchCriteria;

/**
 * @author Ermakovich Kseniya
 */
public interface CriteriaBuilder {

    Criteria apply(DoctorSearchCriteria searchCriteria);

}
