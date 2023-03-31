package solvd.laba.ermakovich.hu.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class Doctor extends UserInfo implements Serializable {

    private Department department;
    private Specialization specialization;
    private Integer cabinet;

}
