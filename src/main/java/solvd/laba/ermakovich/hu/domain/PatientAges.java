package solvd.laba.ermakovich.hu.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ermakovich Kseniya
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientAges implements Serializable {

    private Integer from;
    private Integer to;

}
