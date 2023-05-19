package solvd.laba.ermakovich.hu.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author Ermakovich Kseniya
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientAges implements Serializable {

    @Field(name = "gte")
    private Integer from;

    @Field(name = "lte")
    private Integer to;

}
