package solvd.laba.ermakovich.hu.repository.elastic.criteria.field.doctor;

import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.stereotype.Component;
import solvd.laba.ermakovich.hu.domain.DoctorSearchCriteria;
import solvd.laba.ermakovich.hu.repository.elastic.criteria.field.Field;

/**
 * @author Ermakovich Kseniya
 */
@Component
public class DepartmentFiled implements Field {

    @Override
    public void apply(final DoctorSearchCriteria searchCriteria,
                      final Criteria criteria) {
        if (searchCriteria.getDepartment() != null) {
            criteria.and(Criteria.where("department")
                    .is(searchCriteria.getDepartment()));
        }
    }

}
