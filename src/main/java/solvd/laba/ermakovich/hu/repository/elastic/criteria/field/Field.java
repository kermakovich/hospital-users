package solvd.laba.ermakovich.hu.repository.elastic.criteria.field;

import org.springframework.data.elasticsearch.core.query.Criteria;
import solvd.laba.ermakovich.hu.domain.DoctorSearchCriteria;

/**
 * @author Ermakovich Kseniya
 */
public interface Field {

    void apply(DoctorSearchCriteria searchCriteria, Criteria criteria);

}
