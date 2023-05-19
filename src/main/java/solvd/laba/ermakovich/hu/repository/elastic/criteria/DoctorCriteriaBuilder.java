package solvd.laba.ermakovich.hu.repository.elastic.criteria;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.stereotype.Component;
import solvd.laba.ermakovich.hu.domain.DoctorSearchCriteria;
import solvd.laba.ermakovich.hu.repository.elastic.criteria.field.Field;

/**
 * @author Ermakovich Kseniya
 */
@Component
@RequiredArgsConstructor
public class DoctorCriteriaBuilder implements CriteriaBuilder {

    private final List<Field> fields;

    @Override
    public Criteria apply(final DoctorSearchCriteria searchCriteria) {
        Criteria criteria = new Criteria();
        fields.forEach(field -> field.apply(searchCriteria, criteria));
        return criteria;
    }

}
