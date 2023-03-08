package solvd.laba.ermakovich.hu.domain;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Ermakovich Kseniya
 */
@Data
@Table(name = "doctors")
public class Doctor {

    @Column("user_id")
    private Long id;
    private Department department;
    private Specialization specialization;
    private Integer cabinet;

}
