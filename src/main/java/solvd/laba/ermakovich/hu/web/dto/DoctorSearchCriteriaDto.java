package solvd.laba.ermakovich.hu.web.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import lombok.Data;
import solvd.laba.ermakovich.hu.domain.Department;
import solvd.laba.ermakovich.hu.domain.Specialization;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class DoctorSearchCriteriaDto {

    private BigDecimal pricePerHourFrom;
    private BigDecimal pricePerHourTo;
    private Department department;
    private ArrayList<Specialization> specializations;
    private String surname;
    private Float experienceFrom;
    private Float experienceTo;
    private Integer patientAge;

}
