package solvd.laba.ermakovich.hu.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import lombok.Data;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class DoctorSearchCriteria {

    private BigDecimal pricePerHourFrom;
    private BigDecimal pricePerHourTo;
    private Department department;
    private ArrayList<Specialization> specialization;
    private String surname;
    private Float experienceFrom;
    private Float experienceTo;
    private Integer patientAge;

}
