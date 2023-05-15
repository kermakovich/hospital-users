package solvd.laba.ermakovich.hu.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class Doctor extends UserInfo implements Serializable {

    @Id
    private String id;
    private Department department;
    private Specialization specialization;
    private Integer cabinet;
    private BigDecimal pricePerHour;
    private Float experience;
    private PatientAges patientAges;

}
