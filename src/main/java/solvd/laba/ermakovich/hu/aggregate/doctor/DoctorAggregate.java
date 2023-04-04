package solvd.laba.ermakovich.hu.aggregate.doctor;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import solvd.laba.ermakovich.hu.aggregate.AggregateRoot;
import solvd.laba.ermakovich.hu.domain.Doctor;

/**
 * @author Ermakovich Kseniya
 */
@Document("doctors")
@Data
public class DoctorAggregate extends AggregateRoot {

    public static final String AGGREGATE_TYPE = "Doctor";
    private Doctor doctor;

    public DoctorAggregate(String id) {
        super(id, AGGREGATE_TYPE);
    }

}
