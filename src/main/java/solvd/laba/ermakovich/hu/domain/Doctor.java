package solvd.laba.ermakovich.hu.domain;

import lombok.Data;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Ermakovich Kseniya
 */
@Data
@Document(indexName = "doctor")
public class Doctor extends UserInfo implements Serializable {

    @Id
    private String id;
    private Department department;
    private Specialization specialization;
    private Integer cabinet;

}
