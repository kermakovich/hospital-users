package solvd.laba.ermakovich.hu.domain;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Ermakovich Kseniya
 */
@Data
@Document(indexName = "doctor")
public class ElasticDoctor {

    @Id
    private String id;
    private BigDecimal pricePerHour;
    private Department department;
    private Specialization specialization;
    private String surname;
    private Float experience;
    private UUID externalId;

    @Field(type = FieldType.Integer_Range)
    private PatientAges patientAges;

}
